package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.*;
import pl.artursienkowski.repository.*;
import pl.artursienkowski.service.bonus.BonusCalculatorService;

import pl.artursienkowski.service.customer.CustomerService;
import pl.artursienkowski.service.offer.AccessoriesService;
import pl.artursienkowski.service.offer.OfferCalculatorService;
import pl.artursienkowski.service.search.CustomerSeacherServisWorker;
import pl.artursienkowski.service.stock.CarsStockService;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("calculatedOffer")
@RequestMapping("/worker")
public class WorkerController {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private OfferRepository offerRepository;
    private CarRepository carRepository;
    private CustomerService customerService;
    private CustomerSeacherServisWorker customerSeacherServisWorker;
    private AccessoriesRepository accessoriesRepository;
    private OfferCalculatorService offerCalculatorService;

    public WorkerController(UserRepository userRepository, CustomerRepository customerRepository, OfferRepository offerRepository,
                            CarRepository carRepository, CustomerService customerService, CustomerSeacherServisWorker customerSeacherServisWorker,
                            AccessoriesRepository accessoriesRepository, OfferCalculatorService offerCalculatorService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.offerRepository = offerRepository;
        this.carRepository = carRepository;
        this.customerService = customerService;
        this.customerSeacherServisWorker = customerSeacherServisWorker;
        this.accessoriesRepository = accessoriesRepository;
        this.offerCalculatorService = offerCalculatorService;
    }

    @ModelAttribute("allCars")
    public List<Car> allCars() {
        return carRepository.findAvalibleCars();
    }

    @ModelAttribute("allOffers")
    public List<Offer> allOffers() {
        return offerRepository.findAll();
    }

    @ModelAttribute("allOfferTypes")
    public List<String> allOfferTypes() {
        List<String> allOfferTypes = Arrays.asList("LEASING", "CREDIT");
        return allOfferTypes;
    }

    @ModelAttribute("allAccessories")
    public List<Accessories> allAccessories() {
        return accessoriesRepository.findAll();
    }

    @ModelAttribute("allStatus")
    public List<String> allStatus() {
        List<String> allStatus = Arrays.asList("CONTACT", "METTING", "DECISION", "TEST RIDE");
        return allStatus;
    }


    @GetMapping("/{id}")
    public String worker(Model model, @PathVariable long id, @RequestParam(required = false) String searchMode,
                         @RequestParam(required = false) String query) {
        User user = userRepository.findById(id).get();
        model.addAttribute("user", user);
        model.addAttribute("customer", customerSeacherServisWorker.executeQuery(searchMode, query, user));
        return "worker/Newhome";
    }

    @GetMapping("/addCustomer/{userId}")
    public String addCustomer(Model model, @PathVariable long userId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", new Customer());
        return "worker/add/addCustomer";
    }

    @GetMapping("/addCustomerConsumer/{userId}")
    public String addConsumerForm(Model model, @PathVariable long userId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", new Customer());
        return "worker/add/addCustomerConsumer";
    }

    @PostMapping("/addCustomerConsumer/{userId}")
    public String addConsumer(@ModelAttribute @Validated({Customer.Consumer.class}) Customer customer,
                              BindingResult result, @PathVariable long userId) {
        if (result.hasErrors()) {
            return "worker/add/addCustomerConsumer";
        }
        customer.setNIP(null);
        customer.setUser(userRepository.findById(userId).get());
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/addCustomerCompany/{userId}")
    public String addComapnyForm(Model model, @PathVariable long userId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", new Customer());
        return "worker/add/addCustomerCompany";
    }

    @PostMapping("/addCustomerCompany/{userId}")
    public String addCompany(@ModelAttribute @Validated({Customer.Company.class}) Customer customer, BindingResult result, @PathVariable long userId) {
        if (result.hasErrors()) {
            return "worker/add/addCustomerCompany";
        }
        customer.setUser(userRepository.findById(userId).get());
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/editCustomer/{userId}/{customerId}")
    public String editCustomerForm(Model model, @PathVariable long userId, @PathVariable long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", customer);

        if ("COMPANY".equals(customer.getType())) {
            return "worker/edit/editCustomerCompany";
        }
        return "worker/edit/editCustomerConsumer";
    }

    @PostMapping("/editCustomer/{userId}/{customerId}")
    public String editCustomer(@ModelAttribute @Validated({Customer.editCustomer.class}) Customer customer, BindingResult result,
                               @PathVariable long userId, @PathVariable long customerId, @RequestParam Car car, Model model) {

        Car customerCar = customerRepository.findById(customerId).get().getCar();

        if ("COMPANY".equals(customer.getType())) {
            if (result.hasErrors()) {
                return "worker/edit/editCustomerCompany";
            }
        }
        if ("CUSTOMER".equals(customer.getType())) {
            if (result.hasErrors()) {
                return "worker/edit/editCustomerConsumer";
            }
        }

        customer.setUser(userRepository.findById(userId).get());
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/customerDetails/{userId}/{customerId}")
    public String customerDetails(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", customerRepository.findById(customerId).get());
        return "worker/customerDetails";
    }

    @GetMapping("/out/{userId}/{customerId}")
    public String outCustomerForm(Model model, @PathVariable long userId, @PathVariable long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        customer.setUser(null);
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/chooseOfferType/{userId}/{customerId}")
    public String chooseOfferType(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());

        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("customer", customer);

        if(customer.getCar() == null) {
            return "worker/carSetError";
        }

        return "worker/chooseOfferType";
    }

    @GetMapping("/customerOffer/{userId}/{customerId}")
    public String addOfferForm(Model model, @PathVariable long userId, @PathVariable long customerId) {
        Customer customer = customerRepository.findById(customerId).get();

        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("car", customer.getCar());
        model.addAttribute("offer", new Offer());
        model.addAttribute("accesoriesPriceSum", customer.getAccessoriesPrice());

        return "worker/calculateOfferForCustomer";
    }

    @PostMapping("/customerOffer/{userId}/{customerId}")
    public String addOffer(@ModelAttribute @Validated({Offer.Financing.class}) Offer offer, BindingResult result, @RequestParam String type,
                           @RequestParam double amount, @RequestParam String periodInput, @RequestParam String repurchaseInput,
                           @RequestParam String contributionInput, @PathVariable long userId, @PathVariable long customerId, Model model) {
        if (result.hasErrors()) {
            return "worker/calculateOfferForCustomer";
        }
        offerCalculatorService.calculateFinancing(offer, type, amount, periodInput, repurchaseInput, contributionInput);

        model.addAttribute("calculatedOffer", offer);
        return "redirect:http://localhost:8080/worker/changeOffer/" + userId + "/" + customerId;
    }

    @GetMapping("/byCash/{userId}/{customerId}")
    public String byCashForm(@PathVariable long userId, @PathVariable long customerId, Model model) {
        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", customer);

        Offer offer = offerCalculatorService.setCashOffer(customer);

        offerRepository.save(offer);
        customer.setOffer(offer);
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/changeOffer/{userId}/{customerId}")
    public String comapreOffer(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("offer", customerRepository.findById(customerId).get().getOffer());
        return "worker/changeCustomerCalculation";
    }

    @PostMapping("/changeOffer/{userId}/{customerId}")
    public String switchOrDropNewOffer(@RequestParam String confirmed, @PathVariable long userId, @PathVariable long customerId, HttpSession session) {
        Customer customer = customerRepository.findById(customerId).get();
        Offer offer = (Offer) session.getAttribute("calculatedOffer");
        if ("yes".equals(confirmed)) {
            offerRepository.save(offer);
            customer.setOffer(offer);
            customerRepository.save(customer);
        }
        return "redirect:http://localhost:8080/worker/" + userId;
    }


    @GetMapping("/editCustomerEmail/{userId}/{customerId}")
    public String editCustomerEmailFrom(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", customerRepository.findById(customerId).get());
        return "worker/edit/editCustomerEmail";
    }

    @PostMapping("/editCustomerEmail/{userId}/{customerId}")
    public String editCustomerEmail(@ModelAttribute @Validated({Customer.editEmail.class}) Customer customer, BindingResult result,
                                    @PathVariable long userId, @RequestParam String email) {
        if (result.hasErrors()) {
            return "worker/edit/editCustomerEmail";
        }
        customer.setEmail(email);
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/sell/{userId}/{customerId}")
    public String sellDetailCustomerForm(Model model, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        Customer customer = customerRepository.findById(customerId).get();
        model.addAttribute("customer", customer);

        if (customer.getCar() == null) {
            return "worker/carSetError";
        } else if (customer.getOffer() == null) {
            return "worker/offerSetError";
        } else {
            return "worker/sellCustomer";
        }
    }

    @PostMapping("/sell/{userId}/{customerId}")
    public String sellDetailCustomer(@RequestParam String confirmed, @PathVariable long userId, @PathVariable long customerId) {
        if ("yes".equals(confirmed)) {
            customerService.sell(customerId);
        }
        return "redirect:http://localhost:8080/worker/" + userId;
    }

}



