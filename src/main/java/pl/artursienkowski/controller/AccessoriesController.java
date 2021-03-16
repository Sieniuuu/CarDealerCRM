package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.Accessories;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.AccessoriesRepository;
import pl.artursienkowski.repository.CustomerRepository;
import pl.artursienkowski.repository.UserRepository;
import pl.artursienkowski.service.offer.AccessoriesService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/accessories")
public class AccessoriesController {

    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private AccessoriesRepository accessoriesRepository;
    private AccessoriesService accessoriesService;

    public AccessoriesController(CustomerRepository customerRepository, UserRepository userRepository,
                                 AccessoriesRepository accessoriesRepository, AccessoriesService accessoriesService) {
        this.customerRepository = customerRepository;
        this.userRepository = userRepository;
        this.accessoriesRepository = accessoriesRepository;
        this.accessoriesService = accessoriesService;
    }

    @GetMapping("/")
    public String allAccessories(Model model) {
        model.addAttribute("accessories", accessoriesRepository.findAll());
        return "accessories/all";
    }

    @GetMapping("/add")
    public String addAccessoriesForm(Model model) {
        model.addAttribute("accessories", new Accessories());
        return "accessories/addAndEdit";
    }

    @PostMapping("/add")
    public String addAccessories(@ModelAttribute @Valid Accessories accessories, BindingResult result) {
        if (result.hasErrors()) {
            return "accessories/addAndEdit";
        }
        accessoriesRepository.save(accessories);
        return "redirect:http://localhost:8080/accessories/";
    }

    @GetMapping("/edit/{id}")
    public String editAccessoriesForm(@PathVariable long id, Model model) {
        model.addAttribute("accessories", accessoriesRepository.findById(id));
        return "accessories/addAndEdit";
    }

    @PostMapping("/edit/{id}")
    public String editAccessorie(@ModelAttribute @Valid Accessories accessories, BindingResult result) {
        if (result.hasErrors()) {
            return "accessories/addAndEdit";
        }
        accessoriesRepository.save(accessories);
        return "redirect:http://localhost:8080/accessories/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAccessories(@PathVariable long id, Model model) {
        accessoriesService.deleteAccessory(id);
        return "redirect:http://localhost:8080/accessories/";
    }

    @GetMapping("/add/{userId}/{customerId}")
    public String addAccesoriesForm(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("customer", customerRepository.findById(customerId).get());
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("accessories", accessoriesRepository.findAll());
        return "worker/add/addCustomerAccessories";
    }

    @PostMapping("/add/{userId}/{customerId}")
    public String addAccessories(@ModelAttribute Customer customer, @PathVariable long userId,
                                 @PathVariable long customerId,
                                 @RequestParam(required = false) List<Accessories> accessories) {
        accessoriesService.addAccessories(customerId, customer, accessories);
        return "redirect:http://localhost:8080/worker/" + userId;
    }


}
