package pl.artursienkowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.artursienkowski.model.*;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById (long id);

    @Query("SELECT c FROM Customer c ORDER BY c.id DESC")
    List<Customer> reversCustomerList();

    @Query("SELECT c FROM Customer c WHERE c.user = :user ORDER BY c.id DESC ")
    List<Customer> findCustomerByUserReverse(@Param("user") User user);

    @Query("SELECT c FROM Customer c WHERE c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByUserAndStatusReverse(@Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.user = :user")
    List<Customer> findCustomerByUser(@Param("user") User user);

    @Query("SELECT c FROM Customer c WHERE c.car = :car")
    List<Customer> findCustomerByCar(@Param("car") Car car);

    @Query("SELECT c FROM Customer c WHERE c.offer = :offer")
    List<Customer> findCustomerByOffer(@Param("offer") Offer offer);

    @Query("SELECT c FROM Customer c WHERE c.user = :user AND c.status = :status")
    List<Customer> findCustomerByUserAndStatus(@Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.lastName LIKE :aaa% AND c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByLastNamePart(@Param("aaa") String lastName, @Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE :bbb% AND c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByFirstNamePart(@Param("bbb") String firstName, @Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.PESEL LIKE :ccc% AND c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByPESELPart(@Param("ccc") String PESEL, @Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.status = :status AND c.user = :user ORDER BY c.id DESC")
    List<Customer> findCustomerByStatus(@Param("status") String status, @Param("user") User user);

    @Query("SELECT c FROM Customer c WHERE c.NIP LIKE :ddd% AND c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByNIPPart(@Param("ddd") String NIP, @Param("user") User user, @Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.type = :type AND c.user = :user AND NOT c.status = :status ORDER BY c.id DESC")
    List<Customer> findCustomerByType(@Param("type") String type, @Param("user") User user, @Param("status") String status);


    ///////////////////////


    @Query("SELECT c FROM Customer c WHERE c.status = :status")
    List<Customer> findCustomerByStatusAdmin(@Param("status") String status);

    @Query("SELECT c FROM Customer c WHERE c.lastName LIKE :aaa%")
    List<Customer> findCustomerByLastNamePartAdmin(@Param("aaa") String lastName);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE :bbb%")
    List<Customer> findCustomerByFirstNamePartAdmin(@Param("bbb") String firstName);

    @Query("SELECT c FROM Customer c WHERE c.PESEL LIKE :ccc%")
    List<Customer> findCustomerByPESELPartAdmin(@Param("ccc") String PESEL);

    @Query("SELECT c FROM Customer c WHERE c.NIP LIKE :ddd%")
    List<Customer> findCustomerByNIPPartAdmin(@Param("ddd") String NIP);

    @Query("SELECT c FROM Customer c WHERE c.type = :type")
    List<Customer> findCustomerByTypeAdmin(@Param("type") String type);


    /////////////////////////

    @Query("SELECT c FROM Customer c WHERE c.email = :email")
    Optional<Customer> findCustomerByEmail(@Param("email") String email);

    @Query("SELECT c FROM Customer c WHERE c.car = :car AND c.status = :status")
    List<Customer> findCustomerByCarAndStatus(@Param("car") Car car, @Param("status") String status);

//    SELECT * FROM customer INNER JOIN customer_accessories ca on customer.id = ca.customer_id WHERE accessories_id = 7;

    @Query(nativeQuery = true, value = "SELECT * FROM stock.customer INNER JOIN stock.customer_accessories ca on stock.customer.id = ca.customer_id WHERE accessories_id = ?1")
    List<Customer> findCustomersByAccessories (Accessories accessories);


}
