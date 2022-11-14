package at.htl.entities;

import javax.persistence.*;

@Entity
@NamedQuery(name = "HardProduct.findAll", query = "SELECT h from HardProduct h")
public class HardProduct extends Product {
    public HardProduct() {
    }

    public HardProduct(String name, double price) {
        super(name, price);
    }
}
