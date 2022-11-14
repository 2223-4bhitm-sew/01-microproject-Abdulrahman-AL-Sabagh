package at.htl.control;

import at.htl.entities.HardProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class HardProductRepository {
    @Inject
    EntityManager entityManager;

    public HardProduct save(HardProduct hardProduct) {
        if (entityManager.contains(hardProduct)) {
            return null;
        }
        return entityManager.merge(hardProduct);
    }

    public List<HardProduct> findAll() {
        return entityManager.createNamedQuery("HardProduct.findAll").getResultList();
    }

    public HardProduct findOneById(long id) {
        return entityManager.find(HardProduct.class, id);
    }

    ;

    public HardProduct updateHardProduct(HardProduct hardProduct) {
        return entityManager.merge(hardProduct);
    }

    public HardProduct deleteHardProduct(long id) {
        HardProduct foundProduct = entityManager.find(HardProduct.class, id);
        entityManager.remove(foundProduct);
        return foundProduct;
    }
}
