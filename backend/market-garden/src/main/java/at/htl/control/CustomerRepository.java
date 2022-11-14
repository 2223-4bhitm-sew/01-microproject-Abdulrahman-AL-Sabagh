package at.htl.control;

import at.htl.entities.Customer;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CustomerRepository {
    @Inject
    EntityManager entityManager;


    public List<Customer> getAllCustomers() {
        List<Customer> customers = entityManager.createNamedQuery("Customer.findAll").getResultList();
        return customers;
    }

    public Customer getCustomer(long id) {
        return entityManager.find(Customer.class, id);

    }


    public Customer save(Customer customer) {
        if (entityManager.contains(customer)) {
            return null;
        }
        return entityManager.merge(customer);
    }

    public Customer update(Customer customer) {
        return entityManager.merge(customer);
    }


    public Customer deleteCustomer(long id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        return customer;
    }
}
