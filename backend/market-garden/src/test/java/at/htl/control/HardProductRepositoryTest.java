package at.htl.control;

import at.htl.entities.HardProduct;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.assertj.db.type.Table;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class HardProductRepositoryTest {

    @Inject
    HardProductRepository hardProductRepository;
    @Inject
    AgroalDataSource dataSource;

    @Test
    @Transactional
    void createAHardProduct() {
        hardProductRepository.persist(new HardProduct("shovel", 30));
        Table table = new Table(dataSource, "HardProduct");
        output(table).toConsole();
        assertThat(table).row(0)
                .column("name").value().isEqualTo("shovel")
                .column("price").value().isEqualTo(30);
    }

    @Test
    void getAHardProduct() {
        createAHardProduct();
        HardProduct hardProduct = hardProductRepository.findById(1L);
        Table table = new Table(dataSource, "HardProduct");
        assertThat(table).row(0)
                .column("name").value().isEqualTo(hardProduct.getName())
                .column("price").value().isEqualTo(hardProduct.getPrice());

    }

}