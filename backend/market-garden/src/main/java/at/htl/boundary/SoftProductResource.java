package at.htl.boundary;

import at.htl.control.SoftProductRepository;
import at.htl.entities.SoftProduct;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/soft-product")
public class SoftProductResource {


    @Inject
    SoftProductRepository softProductRepository;

    @POST
    @Transactional
    public Response createSoftProduct(SoftProduct softProduct, @Context UriInfo uriInfo) {
        softProductRepository.persist(softProduct);
        URI uri = uriInfo.getAbsolutePathBuilder().path(softProduct.getId().toString()).build();
        return Response.created(uri).location(uri).build();
    }

    @GET
    public List<SoftProduct> getAll() {
        return softProductRepository.listAll();
    }

    @GET
    @Path("{id}")
    public SoftProduct getSoftProduct(@PathParam("id") long id) {
        return softProductRepository.findById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateSoftProduct(SoftProduct softProduct, @PathParam("id") long id, @Context UriInfo uriInfo) {
        softProductRepository.update("name=?1 price=?2 where id=?3", softProduct.getName(), softProduct.getPrice(), id);
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteSoftProduct(@PathParam("id") long id, @Context UriInfo uriInfo) {
        softProductRepository.delete(softProductRepository.findById(id));
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }

}
