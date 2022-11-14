package at.htl.boundary;

import at.htl.control.HardProductRepository;
import at.htl.entities.HardProduct;
import at.htl.entities.Product;
import org.jboss.resteasy.annotations.jaxrs.HeaderParam;

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
public class HardProductResource {
    @Inject
    HardProductRepository hardProductRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<HardProduct> getAll() {
        return hardProductRepository.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HardProduct getProductById(@PathParam("id") long id) {
        return hardProductRepository.findOneById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createHardProduct(HardProduct hardProduct, @Context UriInfo uriInfo) {
        Product product = hardProductRepository.save(hardProduct);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(product.getId().toString())
                .build();
        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHardProduct(HardProduct hardProduct, @Context UriInfo uriInfo) {
        Product product = hardProductRepository.updateHardProduct(hardProduct);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(product.getId().toString())
                .build();
        return Response.created(uri).build();
    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response deleteHardProduct(@PathParam("id") long id, @Context UriInfo uriInfo) {
        Product product = hardProductRepository.deleteHardProduct(id);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(product.getId().toString())
                .build();
        return Response.created(uri).build();
    }


}
