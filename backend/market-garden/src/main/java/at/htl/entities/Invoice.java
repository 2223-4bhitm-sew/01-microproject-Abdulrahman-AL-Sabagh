package at.htl.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "Invoice.findAll", query = "SELECT  i from  Invoice i ")

public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double totalCost;
    @ManyToOne
    private Customer customer;


    public Invoice(double totalCost, Customer customer) {
        this.totalCost = totalCost;
        this.customer = customer;
    }

    public Invoice() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", totalCost=" + totalCost +
                ", customer=" + customer +
                '}';
    }
}
