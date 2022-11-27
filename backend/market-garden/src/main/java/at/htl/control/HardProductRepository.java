package at.htl.control;

import at.htl.entities.HardProduct;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HardProductRepository implements PanacheRepository<HardProduct> {

}
