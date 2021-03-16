package pl.artursienkowski.service.search;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.CustomerRepository;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerSeacherServisAdmin {

    private CustomerRepository customerRepository;

    public CustomerSeacherServisAdmin(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> executeQuery(String searchMode, String query) {
            if (StringUtils.isEmpty(searchMode)) {
                return customerRepository.reversCustomerList();
            }

            if(StringUtils.isEmpty(query)) {
                return customerRepository.reversCustomerList();
            }

            switch (searchMode) {
                case "firstName":
                    return customerRepository.findCustomerByFirstNamePartAdmin(query);
                case "lastName":
                    return customerRepository.findCustomerByLastNamePartAdmin(query);
                case "type":
                    return customerRepository.findCustomerByTypeAdmin(query);
                case "pesel":
                    return customerRepository.findCustomerByPESELPartAdmin(query);
                case "nip":
                    return customerRepository.findCustomerByNIPPartAdmin(query);
                case "status":
                    return customerRepository.findCustomerByStatusAdmin(query);
            }

            return Collections.emptyList();
        }


}
