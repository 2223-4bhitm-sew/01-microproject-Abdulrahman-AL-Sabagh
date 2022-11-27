package at.htl.control;

import at.htl.entities.Gardener;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GardenerRepository implements PanacheRepository<Gardener> {

}

