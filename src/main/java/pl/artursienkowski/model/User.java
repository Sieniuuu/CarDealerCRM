package pl.artursienkowski.model;

import org.hibernate.validator.constraints.UniqueElements;
import org.hibernate.validator.constraints.pl.PESEL;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank @Size(min = 2, max = 15)
    private String userName;

    @Email
    private String email;

    @Pattern(regexp = "(^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$)",
            message = "password does not meet the requirements")
    private String password;

    @NotBlank @Size(min = 3, max = 15)
    private String firstName;

    @NotBlank @Size(min = 3, max = 15)
    private String lastName;

    @PESEL
    private String PESEL;

    @NotBlank
    private String branch;

    @NotBlank
    private String type;

    @OneToMany (mappedBy = "user")
    List<Customer> customers = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getFullName() {
        return String.join(" ", firstName, lastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullDescription() {
        return String.join(" | ", getFullName(), branch, type);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static double calculateBonus (String type, double carsValue, double accessoriesValue) {
        double bonus = 0;
        switch (type) {
            case "DORADCA":
                bonus = (carsValue * 0.01) + (accessoriesValue * 0.1);
                break;
            case "STARSZY DORADCA":
                bonus = (carsValue * 0.02)  + (accessoriesValue * 0.15);
                break;
            case "EKSPERT":
                bonus = (carsValue * 0.04)  + (accessoriesValue * 0.2);
                break;
        }
        return bonus;
    }
}
