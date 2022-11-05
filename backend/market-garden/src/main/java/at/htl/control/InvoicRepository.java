package at.htl.control;

import at.htl.boundary.Invoice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public
class InvoiceRepository {
    @Inject
    EntityManager entityManager;

    public Invoice create(Invoice invoice) {
        if (entityManager.contains(invoice)) {
            return null;

        }
        return entityManager.merge(invoice);
    }

    public Invoice delete(Invoice invoice) {
        if (entityManager.contains(invoice)) {
            entityManager.remove(invoice);
            return invoice;
        }
        return null;
    }
}
