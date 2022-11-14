package at.htl.control;

import at.htl.entities.Product;
import at.htl.entities.SoftProduct;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class SoftProductRepository {
    @Inject
    EntityManager entityManager;

    public SoftProduct findById(long id) {
        return entityManager.find(SoftProduct.class, id);
    }

    public List<SoftProduct> findAll() {
        return entityManager.createNamedQuery("SoftProduct.findAll").getResultList();
    }

    public SoftProduct save(SoftProduct product) {
        if (entityManager.contains(product)) {
            return null;
        }
        return entityManager.merge(product);
    }

    public SoftProduct updateSoftProduct(SoftProduct product) {
        return entityManager.merge(product);
    }

    public SoftProduct deleteSoftProduct(long id) {
        SoftProduct product = entityManager.find(SoftProduct.class, id);
        entityManager.remove(product);
        return product;
    }
}
