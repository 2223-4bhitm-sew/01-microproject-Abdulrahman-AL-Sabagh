package at.htl.boundary;

import at.htl.control.GardenerRepository;
import at.htl.entities.Gardener;

import javax.inject.Inject;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

@Path("/gardener")
public class GardenerResource {
    @Inject
    GardenerRepository gardenerRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Gardener> getAllGardeners() {
        return gardenerRepository.getAllGardeners();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createGardener(Gardener gardener, @Context UriInfo uriInfo) {
        Gardener createdGardener = gardenerRepository.save(gardener);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(createdGardener.getId().toString())
                .build();
        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateGardener(Gardener gardener, @Context UriInfo uriInfo) {
        Gardener updatedGardener = gardenerRepository.updateGardener(gardener);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(updatedGardener.getId().toString())
                .build();
        return Response.created(uri).build();

    }

    @DELETE
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Response updateGardener(@PathParam("id") long id, @Context UriInfo uriInfo) {
        Gardener updatedGardener = gardenerRepository.deleteGardener(id);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(updatedGardener.getId().toString())
                .build();
        return Response.created(uri).build();

    }
}
