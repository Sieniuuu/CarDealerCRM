package pl.artursienkowski.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import org.springframework.stereotype.Service;
import pl.artursienkowski.model.Accessories;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.CustomerRepository;
import pl.artursienkowski.repository.UserRepository;

import java.util.List;

@Service
public class EmailSendService {


    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private EmailConfig emailConfig;

    public EmailSendService( UserRepository userRepository,
                            CustomerRepository customerRepository, EmailConfig emailConfig) {

        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.emailConfig = emailConfig;
    }

    public void sendOfferDetail(long customerId, long userId) {
        Customer customer = customerRepository.findById(customerId).get();

        StringBuilder allAccessories = new StringBuilder();
        customer.getAccessories().forEach(a -> allAccessories.append(a.toString()).append(" | "));

        String messageToCustomer = customer.getCar().toString() + " " + customer.getOffer().toString() + " " + allAccessories;

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(userRepository.findById(userId).get().getEmail());
        mailMessage.setTo(customer.getEmail());
        mailMessage.setSubject("Volvo - details about offer for " + customer.getFullName());
        mailMessage.setText(messageToCustomer);

        mailSender.send(mailMessage);
    }


}
