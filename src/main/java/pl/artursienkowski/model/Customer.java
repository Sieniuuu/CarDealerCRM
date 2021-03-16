package pl.artursienkowski.model;

import org.hibernate.validator.constraints.pl.NIP;
import org.hibernate.validator.constraints.pl.PESEL;
import pl.artursienkowski.validation.EmailValidation;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity
public class Customer {

    private static final String DATE_FROMATTER = "yyyy-MM-dd HH:mm:ss";

    public interface Company{
    };

    public interface Consumer{
    };

    public interface editCustomer{
    };

    public interface editEmail{
    };

    public interface adminAcces{
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(groups = { Company.class, Consumer.class, editCustomer.class, adminAcces.class })
    @Size(min = 3, max = 15, groups = { Company.class, editCustomer.class, adminAcces.class })
    private String firstName;

    @NotBlank(groups = { Company.class, Consumer.class, adminAcces.class })
    @Size(min = 3, max = 15, groups = { Company.class, editCustomer.class, adminAcces.class })
    private String lastName;

    @NotBlank(groups = { Company.class, Consumer.class, editEmail.class, adminAcces.class })
    @Email(groups = { Company.class, Consumer.class, editEmail.class, adminAcces.class })
    @EmailValidation(groups = { Company.class, Consumer.class, editEmail.class })
    @Column(unique = true)
    private String email;

    @NotBlank(groups = { Company.class, Consumer.class, editCustomer.class, adminAcces.class })
    private String type;

    @NIP(groups = { Company.class })
    private String NIP;

    @PESEL(groups = { Company.class, Consumer.class, adminAcces.class})
    private String PESEL;

    @NotBlank(groups = { Company.class, Consumer.class, editCustomer.class, adminAcces.class })
    private String address;

    @NotBlank(groups = { Company.class, Consumer.class, editCustomer.class, adminAcces.class })
    private String status;

    private String notes;

    private String createdOn;
    private LocalDateTime updateOn;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Offer offer;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Accessories> accessories = new ArrayList<>();


    public List<Accessories> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessories> accessories) {
        this.accessories = accessories;
    }

    public String getFullNameWithPesel() {
        return String.join(" ", firstName, lastName, PESEL);
    }

    public String getFullName() {
        return String.join(" ", firstName, lastName);
    }

    public double getAccessoriesPrice() {
        if (accessories.size() > 0) {
            return accessories.stream().map(Accessories::getPrice).reduce(Double::sum).get();
        } else {
            return 0;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(LocalDateTime updateOn) {
        this.updateOn = updateOn;
    }

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FROMATTER);
        createdOn = now.format(formatter);
    }

    @PreUpdate
    public void preUpdate() {
        updateOn = LocalDateTime.now();
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }



}
