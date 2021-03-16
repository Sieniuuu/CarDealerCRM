package pl.artursienkowski.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Offer {

    public interface Financing{
    };

    public interface Cash{
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(groups = { Financing.class, Cash.class })
    private String type;

    @Min(value = 50000, groups = { Financing.class, Cash.class })
    @Max(value = 450000, groups = { Financing.class, Cash.class })
    private double amount;

    @Min(value = 0, groups = { Financing.class } )
    private double contribution;

    @Min(value = 1000, groups = { Financing.class } )
    private double repurchase;

    @Min(value = 12, groups = { Financing.class } )
    @Max(value = 120, groups = { Financing.class })
    private double period;

    private double instalment;

    @OneToMany(mappedBy = "offer")
    List<Customer> customers = new ArrayList<>();

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProposition() {
        return String.join(", ", type, String.valueOf(amount), String.valueOf(contribution),
                String.valueOf(repurchase), String.valueOf(period), String.valueOf(instalment));

    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getContribution() {
        return contribution;
    }

    public void setContribution(Double contribution) {
        this.contribution = contribution;
    }

    public double getRepurchase() {
        return repurchase;
    }

    public void setRepurchase(Double repurchase) {
        this.repurchase = repurchase;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(Double period) {
        this.period = period;
    }

    public double getInstalment() {
        return instalment;
    }

    public void setInstalment(Double instalment) {
        this.instalment = instalment;
    }

    public static double calcualteInstament(String type, double amount, double period,
                                            double repurchase, double contribution) {
        double instalment;
        if ("leasing".equals(type)) {
            instalment = ((amount - contribution - repurchase) * 1.1) / period;
        } else {
            instalment = ((amount - contribution - repurchase) * 1.2) / period;
        }
        return instalment;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    @Override
    public String toString() {
        return " Offer - " +
                "type: " + type +
                ", amount: " + amount +
                ", contribution: " + contribution +
                ", repurchase: " + repurchase +
                ", period: " + period +
                ", instalment: " + instalment;
    }
}
