package pl.artursienkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.model.Customer;
import pl.artursienkowski.model.User;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findById (long id);

    @Query("SELECT c FROM Car c ORDER BY c.id DESC")
    List<Car> reversCarList();

    @Query("SELECT c FROM Car c WHERE c.body = :body")
    List<Car> findCarsByBody(@Param("body") String body);

    @Query("SELECT c FROM Car c WHERE c.engine = :engine")
    List<Car> findCarsByEngine(@Param("engine") String engine);

    @Query("SELECT c FROM Car c WHERE c.gearbox = :gearbox")
    List<Car> findCarsByGearbox(@Param("gearbox") String gearbox);

    @Query("SELECT c FROM Car c WHERE c.price < :price")
    List<Car> findCarsByPrice(@Param("price") BigDecimal price);

    @Query("SELECT c FROM Car c WHERE c.version = :version")
    List<Car> findCarsByVersion(@Param("version") String version);

    @Query("SELECT c FROM Car c WHERE c.name = :name")
    List<Car> findCarsByName(@Param("name") String name);

    @Query("SELECT c FROM Car c WHERE c.quantity > 0")
    List<Car> findAvalibleCars();

    @Query("SELECT c FROM Car c WHERE c.quantity > :quantity")
    List<Car> findCarsByQuantity(@Param("quantity") int quantity);

}
