package at.htl.boundary;

import at.htl.control.CustomerRepository;
import at.htl.entities.Customer;
import org.jboss.logging.Logger;
import org.jboss.logging.annotations.Param;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.awt.*;
import java.net.URI;
import java.util.List;

@Path("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerRepository repository;
    @Inject
    Logger logger;

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") long id) {
        return repository.findById(id);
    }

    @GET
    public List<Customer> getAllCustomers() {
        return repository.listAll();
    }

    @POST
    @Transactional

    public Response createCustomer(Customer customer, @Context UriInfo uriInfo) {
        repository.persist(customer);
        logger.info(customer);
        URI uri = uriInfo.getAbsolutePathBuilder().build(customer.toString());
        return Response.created(uri).status(201).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateCustomer(Customer customer, @PathParam("id") Long id, @Context UriInfo uriInfo) {

        repository.update("name=?1 where id=?2", customer.getName(), id);
        URI uri = uriInfo.getAbsolutePathBuilder().build(customer.toString());
        return Response.accepted(uri).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") long id, @Context UriInfo uriInfo) {
        Customer customer = repository.findById(id);
        if (customer == null) throw new NotFoundException();
        repository.delete(customer);
        URI uri = uriInfo.getAbsolutePathBuilder().build(id);

        return Response.accepted(uri).build();
    }
}
