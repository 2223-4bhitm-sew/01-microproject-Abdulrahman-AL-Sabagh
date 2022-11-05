package at.htl.entities;

import javax.persistence.Entity;


@Entity
public class SoftProduct extends Product {
    public SoftProduct(String name, double price) {
        super(name, price);
    }

    public SoftProduct() {

    }
}
