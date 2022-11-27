package at.htl.control;

import at.htl.entities.Product;
import at.htl.entities.SoftProduct;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;

@QuarkusTest
class SoftProductRepositoryTest {
    @Inject
    AgroalDataSource dataSource;

    @Inject
    SoftProductRepository softProductRepository;

    @Transactional
    @Test
    void createASoftProduct() {

        softProductRepository.save(new SoftProduct("chinese design", 2000));
        Table table = new Table(dataSource, "SoftProduct");
        assertThat(table).row(0)
                .column("name").value().isEqualTo("chinese design")
                .column("price").value().isEqualTo(2000);
    }

    @Test
    void getASoftProduct() {
        createASoftProduct();
        SoftProduct softProduct = softProductRepository.findById(1);
        Table table = new Table(dataSource, "SoftProduct");
        output(table).toConsole();
        assertThat(table)
                .row(0)
                .column("name").value().isEqualTo(softProduct.getName())
                .column("price").value().isEqualTo(softProduct.getPrice());


    }
}