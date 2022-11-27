package at.htl.boundary;


import at.htl.control.CustomerRepository;
import at.htl.control.GardenerRepository;
import at.htl.entities.Customer;
import at.htl.entities.Gardener;
import at.htl.entities.dto.CustomerDTO;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/gardener")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GardenerResource {
    @Inject
    GardenerRepository repository;

    @POST
    @Transactional
    public Response createGardener(Gardener gardener, @Context UriInfo uriInfo) {
        repository.persist(gardener);
        URI uri = uriInfo.getAbsolutePathBuilder().path(gardener.getId().toString()).build();

        return Response.created(uri).build();
    }

    @GET
    @Path("{id}")
    public Gardener getById(@PathParam("id") long id) {

        return repository.findById(id);
    }
    @GET
    public List<Gardener> getAll() {
        return repository.listAll();
    }
    @PUT
    @Transactional
    @Path("{id}")
    public Response updateGardener(@PathParam("id") long id, @Context UriInfo uriInfo, Gardener gardener) {
        repository.update("name=?1 where id=?2", gardener.getName(), id);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteGardener(@PathParam("id") long id, @Context UriInfo uriInfo) {
        repository.delete(repository.findById(id));
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }


}
