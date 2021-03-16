package pl.artursienkowski.service.offer;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.artursienkowski.model.Accessories;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.AccessoriesRepository;
import pl.artursienkowski.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessoriesService {

    private CustomerRepository customerRepository;
    private AccessoriesRepository accessoriesRepository;


    public AccessoriesService(CustomerRepository customerRepository, AccessoriesRepository accessoriesRepository) {
        this.customerRepository = customerRepository;
        this.accessoriesRepository = accessoriesRepository;
    }

    public void addAccessories(long customerId, Customer customer, List<Accessories> accessories) {
        List<Accessories> accessoriesBeforeEdit = customerRepository.findById(customerId).get().getAccessories();

        if (CollectionUtils.isEmpty(accessories)) {
            customer.setAccessories(null);
            customerRepository.save(customer);
        } else {
            List<Accessories> differences = accessories.stream()
                    .filter(a -> !accessoriesBeforeEdit.contains(a))
                    .collect(Collectors.toList());
            if (differences.size() > 0) {
                customer.setOffer(null);
            }
            customerRepository.save(customer);
        }
    }

    public void deleteAccessory(long id) {
        Accessories toRemove = accessoriesRepository.findById(id).get();
        List<Customer> customersByAccessories = customerRepository.findCustomersByAccessories(toRemove);
        for (Customer c : customersByAccessories) {
            c.getAccessories().remove(toRemove);
            customerRepository.save(c);
        }
        accessoriesRepository.delete(toRemove);
    }
}


