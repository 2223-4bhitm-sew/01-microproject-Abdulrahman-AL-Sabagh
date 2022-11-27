package at.htl.boundary;

import at.htl.control.HardProductRepository;
import at.htl.entities.HardProduct;
import org.hibernate.event.service.spi.EventListenerGroup;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/hard-product")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class HardProductResource {
    @Inject
    HardProductRepository hardProductRepository;

    @POST
    @Transactional
    public Response createHardProduct(HardProduct hardProduct, @Context UriInfo uriInfo) {
        hardProductRepository.persist(hardProduct);
        URI uri = uriInfo.getAbsolutePathBuilder().path(hardProduct.getId().toString()).build();
        return Response.created(uri).location(uri).build();
    }

    @GET
    @Path("{id}")
    public HardProduct getHardProduct(@PathParam("id") long id) {
        return hardProductRepository.findById(id);
    }
    @GET
    public List<HardProduct> getAll() {
        return hardProductRepository.listAll();
    }
    @PUT
    @Path("{id}")
    @Transactional
    public Response updateHardProduct(HardProduct hardProduct, @PathParam("id") long id, @Context UriInfo uriInfo) {
        hardProductRepository.update("name=?1 price=?2 where id=?3", hardProduct.getName(), hardProduct.getPrice(), id);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteHardProduct(@PathParam("id") long id, @Context UriInfo uriInfo) {
        hardProductRepository.delete(hardProductRepository.findById(id));
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }

}
