package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.*;
import pl.artursienkowski.repository.*;
import pl.artursienkowski.service.customer.CustomerService;


import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private OfferRepository offerRepository;
    private UserRepository userRepository;
    private CustomerService customerService;


    public CustomerController(CarRepository carRepository, CustomerRepository customerRepository,
                              OfferRepository offerRepository, UserRepository userRepository, CustomerService customerService) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.customerService = customerService;
    }

    @ModelAttribute("allCars")
    public List<Car> allCars() {
        return carRepository.findAll();
    }

    @ModelAttribute("allOffers")
    public List<Offer> allOffers() {
        return offerRepository.findAll();
    }

    @ModelAttribute("allTypes")
    public List<String> allTypes() {
        List<String> allTypes = Arrays.asList("COMPANY", "CONSUMER");
        return allTypes;
    }

    @ModelAttribute("allStatus")
    public List<String> allStatus() {
        List<String> allStatus = Arrays.asList("CONTACT", "OUT", "SELL", "METTING");
        return allStatus;
    }

    @ModelAttribute("allUsers")
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/")
    public String customerList(Model model) {
        model.addAttribute("customer", customerRepository.reversCustomerList());
        return "customer/all";
    }

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/addAndEdit";
    }

    @PostMapping("/add")
    public String addCustomer(@ModelAttribute @Validated({Customer.adminAcces.class}) Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/addAndEdit";
        }
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/customer/";
    }

    @GetMapping("/edit/{id}")
    public String editCustomerForm(@PathVariable long id, Model model) {
        model.addAttribute("customer", customerRepository.findById(id).get());
        return "customer/addAndEdit";
    }

    @PostMapping("/edit/{id}")
    public String editCustomer(@ModelAttribute @Validated({Customer.adminAcces.class}) Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return "customer/addAndEdit";
        }
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/customer/";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomerForm(@PathVariable long id, Model model) {
        customerService.deleteCustomer(customerRepository.findById(id).get());
        return "redirect:http://localhost:8080/customer/";
    }

}


