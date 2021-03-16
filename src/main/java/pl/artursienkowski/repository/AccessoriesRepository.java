package pl.artursienkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.artursienkowski.model.Accessories;
import pl.artursienkowski.model.Car;
import pl.artursienkowski.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AccessoriesRepository extends JpaRepository<Accessories, Long> {

    Optional<Accessories> findById (long id);
}
