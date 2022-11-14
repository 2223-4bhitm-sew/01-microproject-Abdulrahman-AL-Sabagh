package at.htl.boundary;

import at.htl.control.SoftProductRepository;
import at.htl.entities.Product;
import at.htl.entities.SoftProduct;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/soft-product")
public class SoftProductResource {
    @Inject
    SoftProductRepository softProductRepository;


    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public SoftProduct getProduct(@PathParam("id") long id) {
        return softProductRepository.findById(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SoftProduct> getAll() {
        return softProductRepository.findAll();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveProduct(@Context UriInfo uriInfo, SoftProduct softProduct) {
        Product product = softProductRepository.save(softProduct);
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
    public Response updateProduct(@Context UriInfo uriInfo, SoftProduct softProduct) {
        Product product = softProductRepository.updateSoftProduct(softProduct);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(product.getId().toString())
                .build();
        return Response.created(uri).build();
    }




    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@Context UriInfo uriInfo,@PathParam("id") long id ) {
        Product product = softProductRepository.deleteSoftProduct(id);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(product.getId().toString())
                .build();
        return Response.created(uri).build();
    }

}
