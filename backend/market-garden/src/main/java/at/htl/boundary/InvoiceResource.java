package at.htl.boundary;

import at.htl.control.InvoiceRepository;
import at.htl.entities.Invoice;
import org.jboss.logging.Logger;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;


@Path("/invoice")
public class InvoiceResource {

    @Inject
    InvoiceRepository invoiceRepository;

    @Inject
    Logger logger;

    @GET
    public List<Invoice> getAll() {
        return null;
    }

    @GET
    @Path("{id}")
    public Invoice getOneInvoice(@PathParam("id") long id) {
        return invoiceRepository.getInvoice(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.getAllCustomers();
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createInvoice(Invoice invoice, @Context UriInfo uriInfo) {
        logger.infof(invoice.toString());
        Invoice createdInvoice = invoiceRepository.save(invoice);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(createdInvoice.getId().toString())
                .build();


        return Response.created(uri).build();
    }

    @PUT
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateInvoice(Invoice invoice, @Context UriInfo uriInfo) {
        Invoice updatedInvoice = invoiceRepository.updateInvoice(invoice);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(updatedInvoice.getId().toString())
                .build();
        return Response.created(uri).build();
    }


    @DELETE
    @Transactional
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInvoice(@PathParam("id") long id, @Context UriInfo uriInfo) {
        Invoice deletedInvoice = invoiceRepository.deleteInvoice(id);
        URI uri = uriInfo
                .getAbsolutePathBuilder()
                .path(deletedInvoice.getId().toString())
                .build();
        return Response.created(null).build();
    }

}
