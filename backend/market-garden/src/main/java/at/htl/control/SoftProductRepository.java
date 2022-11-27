package at.htl.control;

import at.htl.entities.SoftProduct;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SoftProductRepository implements PanacheRepository<SoftProduct> {

}
