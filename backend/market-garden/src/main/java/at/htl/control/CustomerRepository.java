package at.htl.control;

import at.htl.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {
    @Inject
    EntityManager entityManager;


    public List getAllCustomers() {
        return entityManager.createNamedQuery("Customer.findAll").getResultList();

    }

    public Customer getCustomer(Customer customer) {
        return entityManager.find(customer.getClass(), customer);
    }

    @Transactional
    public Customer save(Customer customer) {
        return entityManager.merge(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return null; //TODO fix this later
    }

    @Transactional
    public Customer deleteCustomer(Customer customer) {
        if (entityManager.contains(customer)) {
            entityManager.remove(customer);
            return customer;

        }
        return null;
    }
}
