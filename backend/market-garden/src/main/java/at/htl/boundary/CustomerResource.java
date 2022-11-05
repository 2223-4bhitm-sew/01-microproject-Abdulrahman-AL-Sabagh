package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entities.Customer;

import javax.inject.Inject;

import org.hibernate.criterion.CriteriaQuery;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/customer")
public class CustomerResource {
    @Inject
    CustomerRepository cr;
    @Inject
    Logger logger;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List findAll() {
        return cr.getAllCustomers();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Customer createCustomer(Customer customer) {
        logger.info(customer);
        return cr.save(customer);
    }



}
