package at.htl.boundary;

import at.htl.control.InvoiceRepository;
import at.htl.entities.Invoice;

import javax.inject.Inject;
import javax.persistence.PostLoad;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.security.DrbgParameters;
import java.util.List;

@Path("/invoice")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InvoiceResource {

    @Inject
    InvoiceRepository invoiceRepository;

    @POST
    @Transactional
    public Response createInvoice(Invoice invoice, @Context UriInfo uriInfo) {
        invoiceRepository.persist(invoice);
        URI uri = uriInfo.getAbsolutePathBuilder().path(invoice.getId().toString()).build();
        return Response.created(uri).location(uri).build();
    }

    @GET
    @Path("{id}")
    public Invoice getInvoice(@PathParam("id") long id) {
        return invoiceRepository.findById(id);

    }

    @GET
    public List<Invoice> getAll() {
        return invoiceRepository.listAll();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Response updateInvoice(Invoice invoice, @PathParam("id") long id, @Context UriInfo uriInfo) {
        invoiceRepository.update("totalCost=?1 where id=?2", invoice.getTotalCost(), invoice.getId());
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public Response deleteInvoice(@PathParam("id") long id, @Context UriInfo uriInfo) {
        invoiceRepository.delete(invoiceRepository.findById(id));
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.valueOf(id).toString()).build();
        return Response.accepted(uri).location(uri).build();
    }

}
