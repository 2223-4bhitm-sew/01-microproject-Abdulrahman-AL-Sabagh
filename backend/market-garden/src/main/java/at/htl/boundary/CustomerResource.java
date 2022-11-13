package at.htl.boundary;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import at.htl.control.CustomerRepository;
import at.htl.entities.Customer;
import org.jboss.logging.Logger;

import java.net.URI;
import java.util.List;

@Path("/customer")
public class CustomerResource {
    @Inject
    CustomerRepository customerRepository;

    @Inject
    Logger logger;


    @GET
    public List<Customer> getUsers() {
        return customerRepository.getAllCustomers();
    }

    @GET
    @Path("{id}")
    public Customer getCustoer(@PathParam("id") long id) {
        return customerRepository.getCustomer(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createUser(Customer customer, @Context UriInfo uriInfo) {
        Customer savedCustomer = customerRepository.save(customer);

        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(savedCustomer.getId().toString())
                .build();
        return Response.created(uri).build();
    }

    @PATCH
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateName(Customer customer, @Context UriInfo uriInfo) {
        customerRepository.update(customer);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(customer.getId().toString())
                .build();
        return Response.created(uri).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response delete(
            @PathParam("id") long id,
            @Context UriInfo uriInfo
    ) {
        logger.info(id);
        Customer customer = customerRepository.deleteCustomer(id);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(customer.getId().toString())
                .build();

        return Response.created(uri).build();
    }

}
