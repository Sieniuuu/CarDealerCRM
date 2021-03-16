package pl.artursienkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.artursienkowski.model.Offer;

import java.util.Optional;


public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findById (long id);

}
