package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.CarRepository;
import pl.artursienkowski.repository.CustomerRepository;
import pl.artursienkowski.repository.UserRepository;
import pl.artursienkowski.service.search.CarSeacherServis;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private CustomerRepository customerRepository;
    private CarSeacherServis carSeacherServis;

    public CarController(UserRepository userRepository, CarRepository carRepository, CustomerRepository customerRepository,
                         CarSeacherServis carSeacherServis) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
        this.carSeacherServis = carSeacherServis;
    }

    @GetMapping ("/")
    public String carsList (Model model, @RequestParam(required = false) String searchMode,
                            @RequestParam(required = false) String query) {
        model.addAttribute("car", carSeacherServis.executeQuery(searchMode, query));
        return "car/all";
    }

    @GetMapping ("/add")
    public String addCarForm (Model model) {
        model.addAttribute("car", new Car());
        return "car/addAndEdit";
    }

    @PostMapping ("/add")
    public String addCar (@ModelAttribute @Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "car/addAndEdit";
        }
        carRepository.save(car);
        return "redirect:http://localhost:8080/car/";
    }

    @GetMapping ("/edit/{id}")
    public String editCarForm (Model model, @PathVariable long id) {
        model.addAttribute("car", carRepository.findById(id).get());
        return "car/addAndEdit";
    }

    @PostMapping ("/edit/{id}")
    public String editCar (@ModelAttribute @Valid Car car, BindingResult result) {
        if (result.hasErrors()) {
            return "car/addAndEdit";
        }
        carRepository.save(car);
        return "redirect:http://localhost:8080/car/";
    }

    @GetMapping ("/delete/{id}")
    public String deleteCarForm (@PathVariable long id, Model model) {
        Car car = carRepository.findById(id).get();
        List<Customer> customerByUser = customerRepository.findCustomerByCar(car);
        for (Customer c : customerByUser) {
            c.setCar(null);
            customerRepository.save(c);
        }
        carRepository.delete(car);

        return "redirect:http://localhost:8080/car/";
    }

    @GetMapping("/setCar/{userId}/{customerId}")
    public String setCarForm(Model model, @RequestParam(required = false) String searchMode, @RequestParam(required = false)
            String query, @PathVariable long userId, @PathVariable long customerId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("customer", customerRepository.findById(customerId).get());
        model.addAttribute("car", carSeacherServis.executeQuery(searchMode, query));
        return "worker/setCar";
    }

    @GetMapping ("/choosenCar/{userId}/{customerId}/{carId}")
    public String setCar (@PathVariable long userId, @PathVariable long customerId, @PathVariable long carId) {
        Customer customer = customerRepository.findById(customerId).get();
        customer.setCar(carRepository.findById(carId).get());
        customer.setOffer(null);
        customer.setAccessories(null);
        customerRepository.save(customer);
        return "redirect:http://localhost:8080/worker/" + userId;
    }

    @GetMapping("/carsStock/{userId}")
    public String carsStock(Model model, @RequestParam(required = false) String searchMode,
                @RequestParam(required = false) String query, @PathVariable long userId) {
        model.addAttribute("user", userRepository.findById(userId).get());
        model.addAttribute("car", carSeacherServis.executeQuery(searchMode, query));
        return "worker/carsStock";
    }
}



