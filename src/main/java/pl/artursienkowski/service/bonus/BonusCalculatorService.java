package pl.artursienkowski.service.bonus;

import org.springframework.stereotype.Service;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;
import pl.artursienkowski.repository.CustomerRepository;

import java.util.List;

@Service
public class BonusCalculatorService {

    public double calculateBonusForUser(User user, List<Customer> customers) {

        double calculateBonus = 0.0;

        if (customers.size() > 0) {
            double carsValue = 0.0;
            double accessoriesValue = 0.0;
            for (Customer c : customers) {
                carsValue += c.getCar().getPrice();
                accessoriesValue += c.getAccessoriesPrice();
            }
            calculateBonus = User.calculateBonus(user.getType(), carsValue, accessoriesValue);
        }
        return calculateBonus;
    }
}
