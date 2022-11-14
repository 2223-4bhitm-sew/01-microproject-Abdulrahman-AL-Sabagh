package at.htl.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@NamedQuery(name = "Gardener.findAll",query = "SELECT g from Gardener g")
public class Gardener {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany
    private Collection<SoftProduct> products = new ArrayList<>();


    public Gardener(String name, Collection<SoftProduct> products) {
        this.name = name;
        this.products = products;
    }

    public Gardener() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<SoftProduct> getProducts() {
        return products;
    }

    public void setProducts(Collection<SoftProduct> products) {
        this.products = products;
    }
}
