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

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String userName);

    @Query("SELECT u FROM User u ORDER BY u.id DESC")
    List<User> reversUserList();

    @Query("SELECT u FROM User u WHERE u.firstName LIKE :aaa%")
    List<User> findUserByFirstNamePart(@Param("aaa") String firstName);

    @Query("SELECT u FROM User u WHERE u.lastName LIKE :bbb%")
    List<User> findUserByLastNamePart(@Param("bbb") String lastName);

    @Query("SELECT u FROM User u WHERE u.PESEL LIKE :ccc%")
    List<User> findUserByPESELPart(@Param("ccc") String PESEL);

    @Query("SELECT u FROM User u WHERE u.branch = :branch")
    List<User> findUserByBranch(@Param("branch") String branch);

    @Query("SELECT u FROM User u WHERE u.type LIKE :ddd%")
    List<User> findUserByType(@Param("ddd") String type);

}
