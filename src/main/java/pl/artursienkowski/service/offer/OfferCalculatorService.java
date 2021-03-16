package pl.artursienkowski.service.offer;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.Offer;
import pl.artursienkowski.repository.CustomerRepository;

@Service
public class OfferCalculatorService {

    private CustomerRepository customerRepository;

    public OfferCalculatorService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public Offer calculateFinancing(Offer offer, String type, double amount, String period,
                           String repurchase, String contribution  ) {

        offer.setContribution(Double.parseDouble(contribution));
        offer.setRepurchase(Double.parseDouble(repurchase));
        offer.setPeriod(Double.parseDouble(period));
        offer.setInstalment(Offer.round(Offer.calcualteInstament(type, amount, Double.parseDouble(period),
                Double.parseDouble(repurchase), Double.parseDouble(contribution)), 2));

        return offer;
    }

    public Offer setCashOffer(Customer customer) {

        double amount = customer.getCar().getPrice() + customer.getAccessoriesPrice();

        Offer offer = new Offer();
        offer.setType("CASH");
        offer.setAmount(amount);
        offer.setContribution(0.0);
        offer.setRepurchase(0.0);
        offer.setPeriod(0.0);
        offer.setInstalment(0.0);

        return offer;
    }



}
