package at.htl.control;

import at.htl.boundary.GardenerResource;
import at.htl.entities.Gardener;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GardenerRepository {
    @Inject
    EntityManager entityManager;


    public List<Gardener> getAllGardeners() {
        return entityManager.createNamedQuery("Gardener.findAll").getResultList();
    }

    public Gardener save(Gardener gardener) {
        return entityManager.merge(gardener);
    }

    public Gardener updateGardener(Gardener gardener) {
        return entityManager.merge(gardener);
    }

    public Gardener deleteGardener(long id) {
        Gardener foundGardener = entityManager.find(Gardener.class, id);
        entityManager.remove(foundGardener);
        return foundGardener;
    }
}

