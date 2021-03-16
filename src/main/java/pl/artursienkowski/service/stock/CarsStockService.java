package pl.artursienkowski.service.stock;

import org.springframework.stereotype.Service;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.repository.CarRepository;
import pl.artursienkowski.repository.CustomerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarsStockService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;


    public CarsStockService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public void carsStockQuantityUpdate () {
        List<Car> carList = carRepository.findAll();

        for (Car car : carList) {
            List<Customer> quantityUpdate = new ArrayList<>();
            List<Customer> customers = customerRepository.findCustomerByCarAndStatus(car, "SELL");
            for(Customer c : customers) {
                if (car.getUpdateOn().isBefore(c.getUpdateOn())) {
                    quantityUpdate.add(c);
                }
            }
            car.setQuantity(car.getQuantity() - quantityUpdate.size());
            car.setUpdateOn(LocalDateTime.now());
            carRepository.save(car);
        }
    }
}
