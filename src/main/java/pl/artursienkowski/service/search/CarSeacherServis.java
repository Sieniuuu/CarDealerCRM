package pl.artursienkowski.service.search;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.repository.CarRepository;
import pl.artursienkowski.service.stock.CarsStockService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class CarSeacherServis {

    private CarRepository carRepository;
    private CarsStockService carsStockService;

    public CarSeacherServis(CarRepository carRepository, CarsStockService carsStockService) {
        this.carRepository = carRepository;
        this.carsStockService = carsStockService;
    }


    public List<Car> executeQuery(String searchMode, String query) {
            carsStockService.carsStockQuantityUpdate();
            if (StringUtils.isEmpty(searchMode)) {
                return carRepository.reversCarList();
            }

            if(StringUtils.isEmpty(query)) {
                return carRepository.reversCarList();
            }

            switch (searchMode) {
                case "body":
                    return carRepository.findCarsByBody(query);
                case "engine":
                    return carRepository.findCarsByEngine(query);
                case "gearbox":
                    return carRepository.findCarsByGearbox(query);
                case "price":
                    return carRepository.findCarsByPrice(BigDecimal.valueOf(Double.parseDouble(query)));
                case "version":
                    return carRepository.findCarsByVersion(query);
                case "name":
                    return carRepository.findCarsByName(query);
                case "quantity":
                    return carRepository.findCarsByQuantity(Integer.parseInt(query));
            }

            return Collections.emptyList();
        }


}
