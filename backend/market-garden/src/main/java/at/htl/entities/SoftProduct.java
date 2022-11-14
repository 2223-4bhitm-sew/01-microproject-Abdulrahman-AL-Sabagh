package at.htl.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(query = "select s from SoftProduct s", name = "SoftProduct.findAll")
public class SoftProduct extends Product {
    public SoftProduct(String name, double price) {
        super(name, price);
    }

    public SoftProduct() {

    }
}
