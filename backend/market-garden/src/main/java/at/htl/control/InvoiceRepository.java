package at.htl.control;

import at.htl.entities.Customer;
import at.htl.entities.Invoice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class InvoiceRepository {

    @Inject
    EntityManager entityManager;


    public Invoice save(Invoice invoice) {
        return entityManager.merge(invoice);
    }

    public List<Invoice> getAllCustomers() {
        List<Invoice> invoices = entityManager.createNamedQuery("Invoice.findAll").getResultList();
        return invoices;
    }

    public Invoice getInvoice(long id) {
        return entityManager.find(Invoice.class, id);
    }

    public Invoice updateInvoice(Invoice invoice) {
        return entityManager.merge(invoice);
    }


    public Invoice deleteInvoice(long id) {
        Invoice invoice = entityManager.find(Invoice.class, id);
        entityManager.remove(invoice);
        return invoice;
    }
}
