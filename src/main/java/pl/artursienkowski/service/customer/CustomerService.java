package pl.artursienkowski.service.customer;

import org.springframework.stereotype.Service;
import pl.artursienkowski.model.Accessories;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {


    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void deleteCustomer(Customer toRemove) {
        toRemove.setAccessories(null);
        customerRepository.save(toRemove);
        customerRepository.delete(toRemove);
    }

    public void sell(long customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        customer.setStatus("SELL");
        customerRepository.save(customer);
    }
}

