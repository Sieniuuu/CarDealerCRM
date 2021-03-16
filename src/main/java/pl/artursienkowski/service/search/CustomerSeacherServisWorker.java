package pl.artursienkowski.service.search;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.CustomerRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CustomerSeacherServisWorker {

    private CustomerRepository customerRepository;

    public CustomerSeacherServisWorker(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> executeQuery(String searchMode, String query, User user) {
            if (StringUtils.isEmpty(searchMode)) {
                return customerRepository.findCustomerByUserAndStatusReverse(user, "SELL");
            }

            if(StringUtils.isEmpty(query)) {
                return customerRepository.findCustomerByUserAndStatusReverse(user, "SELL");
            }

            switch (searchMode) {
                case "firstName":
                    return customerRepository.findCustomerByFirstNamePart(query, user, "SELL");
                case "lastName":
                    return customerRepository.findCustomerByLastNamePart(query, user, "SELL");
                case "type":
                    return customerRepository.findCustomerByType(query, user, "SELL");
                case "pesel":
                    return customerRepository.findCustomerByPESELPart(query, user, "SELL");
                case "nip":
                    return customerRepository.findCustomerByNIPPart(query, user, "SELL");
                case "status":
                    List<Customer> soldCustomers = new ArrayList<>();
                    if(query.equals("SELL")) {
                        return soldCustomers;
                    }
                    return customerRepository.findCustomerByStatus(query, user);
            }

            return Collections.emptyList();
        }


}
