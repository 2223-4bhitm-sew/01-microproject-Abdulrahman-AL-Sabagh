package at.htl.boundary;

import at.htl.control.InvoiceRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/invoice")
public class Invoice {
    @Inject
    InvoiceRepository invoiceRepository;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice create(Invoice invoice) {
        return invoiceRepository.create(invoice);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Invoice delete(Invoice invoice) {
        return invoiceRepository.delete(invoice);
    }
}

