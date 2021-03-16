package pl.artursienkowski.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.CustomerRepository;
import pl.artursienkowski.repository.UserRepository;
import pl.artursienkowski.service.bonus.BonusCalculatorService;
import pl.artursienkowski.service.search.UserSeacherServis;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private UserSeacherServis userSeacherServis;
    private BonusCalculatorService bonusCalculatorService;

    public UserController(UserRepository userRepository, CustomerRepository customerRepository,
                          UserSeacherServis userSeacherServis, BonusCalculatorService bonusCalculatorService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.userSeacherServis = userSeacherServis;
        this.bonusCalculatorService = bonusCalculatorService;
    }

    @ModelAttribute("allTypes")
    public List<String> allTypes() {
        List<String> allTypes = Arrays.asList("DORADCA", "STARSZY DORADCA", "EKSPERT", "ADMINISTRATOR");
        return allTypes;
    }

    @ModelAttribute("allBranch")
    public List<String> allBranch() {
        List<String> allBranch = Arrays.asList("WAW01", "WAW02", "WAW03", "KRA1", "WRO01", "GDA01", "GDA02", "KAT01", "SZN1", "POZ1", "POZ2", "HEAD");
        return allBranch;
    }

    @GetMapping("/")
    public String allUsers(Model model, @RequestParam(required = false) String searchMode,
                           @RequestParam(required = false) String query) {
        model.addAttribute("user", userSeacherServis.executeQuery(searchMode, query));

        return "user/all";
    }

    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/addAndEdit";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute @Valid User user, BindingResult result, @RequestParam String password) {
        if (result.hasErrors()) {
            return "user/addAndEdit";
        }
        userRepository.save(user);
        return "redirect:http://localhost:8080/user/";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "user/addAndEdit";
    }

    @PostMapping("/edit/{id}")
    public String editUser(@ModelAttribute @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            return "user/addAndEdit";
        }
        userRepository.save(user);
        return "redirect:http://localhost:8080/user/";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable long id, Model model) {
        User user = userRepository.findById(id).get();
        List<Customer> customerByUser = customerRepository.findCustomerByUser(user);
        for (Customer c : customerByUser) {
            c.setUser(null);
            customerRepository.save(c);
        }
        userRepository.delete(user);
        return "redirect:http://localhost:8080/user/";
    }

    @GetMapping("/bonusCalculator/{userId}")
    public String bonusCalculator(Model model, @PathVariable long userId) {
        User user = userRepository.findById(userId).get();
        List<Customer> customers = customerRepository.findCustomerByUserAndStatus(user, "SELL");

        model.addAttribute("user", user);
        model.addAttribute("customer", customers);
        model.addAttribute("bonus", bonusCalculatorService.calculateBonusForUser(user, customers));

        return "worker/bonusCalculator";
    }

    @GetMapping("/editProfil/{userId}")
    public String editProfilForm(@RequestParam(required = false) String error, Model model, @PathVariable long userId) {
        if("true".equals(error)) {
            model.addAttribute("error", "THE OLD PASSWORD IS INNCORECT OR THE NEW PASSWORDS DO NOT MATCH!");
            model.addAttribute("user", userRepository.findById(userId).get());
            return "worker/edit/editUserPassword";
        }
        model.addAttribute("user", userRepository.findById(userId).get());
        return "worker/edit/editUserPassword";
    }

    @PostMapping("/editProfil/{userId}")
    public String editProfil(@PathVariable long userId, @RequestParam String password,
                             @RequestParam String firstNewPassword, @RequestParam String secondNewPassword, Model model) {
        model.addAttribute("user", userRepository.findById(userId).get());
        User user = userRepository.findById(userId).get();
        String pattern = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}";

        if (password.matches(pattern) && firstNewPassword.matches(pattern) && secondNewPassword.matches(pattern)) {
            if (user.getPassword().equals(password) && firstNewPassword.equals(secondNewPassword)) {
                user.setPassword(firstNewPassword);
            } else {
                return "redirect:http://localhost:8080/user/editProfil/" + userId + "?error=true";
            }
        } else {
            return "redirect:http://localhost:8080/user/editProfil/" + userId + "?error=true";
        }
        userRepository.save(user);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

}

