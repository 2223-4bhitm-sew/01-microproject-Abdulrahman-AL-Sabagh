package at.htl.control;

import at.htl.entities.Invoice;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class InvoiceRepositoryTest {

    @Inject
    InvoiceRepository invoiceRepository;
    @Inject
    AgroalDataSource dataSource;

    @Test
    @Transactional
    void createAnInvoice() {
        invoiceRepository.persist(new Invoice(100, null));
        Table table = new Table(dataSource, "Invoice");
        output(table).toConsole();
        assertThat(table).row(0).column("totalcost").value().isEqualTo(100);
    }

    @Test
    void getAnInvoice() {
        createAnInvoice();
        Invoice invoice = invoiceRepository.findById(1L);
        Table table = new Table(dataSource, "Invoice");
        assertThat(table).row(0).column("totalcost").value().isEqualTo(invoice.getTotalCost());
    }
}