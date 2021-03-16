package pl.artursienkowski.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.Offer;
import pl.artursienkowski.repository.CustomerRepository;
import pl.artursienkowski.repository.OfferRepository;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("calculatedOffer")
@RequestMapping("/offer")

public class OfferController {

    private OfferRepository offerRepository;
    private CustomerRepository customerRepository;

    public OfferController(OfferRepository offerRepository, CustomerRepository customerRepository ) {
        this.offerRepository = offerRepository;
        this.customerRepository = customerRepository;
    }

    @ModelAttribute("allOfferTypes")
    public List<String> allTypes() {
        List<String> offerTypes = Arrays.asList("leasing", "kredyt");
        return offerTypes;
    }

    @GetMapping("/")
    public String offerList(Model model) {
        model.addAttribute("offer", offerRepository.findAll());
        return "offer/all";
    }

    @GetMapping("/add")
    public String addOfferForm(Model model) {
        model.addAttribute("offer", new Offer());
        return "offer/addAndEdit";
    }

    @PostMapping("/add")
    public String addOffer(@ModelAttribute @Valid Offer offer, BindingResult result, @RequestParam String type,
                           @RequestParam double amount, @RequestParam double period, @RequestParam double repurchase,
                           @RequestParam double contribution, Model model) {
        if (result.hasErrors()) {
            return "offer/addAndEdit";
        }
        offer.setInstalment(Offer.round(Offer.calcualteInstament(type, amount, period, repurchase, contribution), 2));
        model.addAttribute("calculatedOffer", offer);

        return "redirect:http://localhost:8080/offer/calculation/";
    }

    @GetMapping("/edit/{id}")
    public String editOfferForm(@PathVariable long id, Model model) {
        model.addAttribute("offer", offerRepository.findById(id).get());
        return "offer/addAndEdit";
    }

    @PostMapping("/edit/{id}")
    public String editOffer(@ModelAttribute @Valid Offer offer, BindingResult result, @RequestParam String type,
                            @RequestParam double amount, @RequestParam double period, @RequestParam double repurchase,
                            @RequestParam double contribution, Model model) {
        if (result.hasErrors()) {
            return "offer/addAndEdit";
        }
        offer.setInstalment(Offer.round(Offer.calcualteInstament(type, amount, period, repurchase, contribution), 2));
        model.addAttribute("calculatedOffer", offer);
        offerRepository.save(offer);

        return "redirect:http://localhost:8080/offer/calculation/";
    }

    @GetMapping("/calculation")
    public String offerCalculation(Model model, HttpSession session) {
        model.addAttribute("offer", (Offer) session.getAttribute("calculatedOffer"));
        model.addAttribute("customer", customerRepository.findAll());
        return "offer/calculation";
    }

    @PostMapping("/calculation")
    public String offerAddCalculation(@RequestParam long id, HttpSession session) {
        Offer offer = (Offer) session.getAttribute("calculatedOffer");
        if (id == 0) {
            return "redirect:http://localhost:8080/offer/";
        }
        offerRepository.save(offer);
        Customer customer = customerRepository.findById(id).get();
        customer.setOffer(offer);
        customerRepository.save(customer);

        return "redirect:http://localhost:8080/offer/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOffer(@PathVariable long id, Model model) {
        Offer offer = offerRepository.findById(id).get();
        List<Customer> customerByOffer = customerRepository.findCustomerByOffer(offer);
        for (Customer c : customerByOffer) {
            c.setOffer(null);
            customerRepository.save(c);
        }
        offerRepository.delete(offer);
        return "redirect:http://localhost:8080/offer/";
    }

}


