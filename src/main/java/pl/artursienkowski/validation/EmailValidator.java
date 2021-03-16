package pl.artursienkowski.validation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.CustomerRepository;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class EmailValidator implements ConstraintValidator<EmailValidation, String> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(String customerEmail, ConstraintValidatorContext constraintValidatorContext) {
      return customerRepository.findAll().stream().map(Customer::getEmail).noneMatch(customerEmail::equals);
    }

    @Override
    public void initialize(EmailValidation constraintAnnotation) {

    }
}
