package at.htl.control;

import at.htl.control.CustomerRepository;
import at.htl.entities.Customer;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
class CustomerRepositoryTest {
    @Inject
    CustomerRepository customerRepository;

    @Inject
    AgroalDataSource dataSource;

    @Test
    @Transactional
    void createACustomer() {
        customerRepository.save(new Customer("Bob"));
        Table table = new Table(dataSource, "Customer");
        assertThat(table).row(0).column("name").value().isEqualTo("Bob");
    }

    @Test
    void getACustomer() {


        Customer customer = customerRepository.getCustomer(1);
        Table table = new Table(dataSource, "Customer");
        output(table).toConsole();
        assertThat(table).row(0).column("name").value()
                .isEqualTo(customer.getName());
    }
}