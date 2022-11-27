package at.htl.control;

import at.htl.entities.Gardener;
import io.agroal.api.AgroalDataSource;
import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Change;
import org.assertj.db.type.Changes;
import org.assertj.db.type.Request;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.swing.event.ChangeListener;
import javax.transaction.Transactional;

import org.assertj.db.type.Table;

import static org.assertj.db.api.Assertions.assertThat;
import static org.assertj.db.output.Outputs.output;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class GardenerRepositoryTest {

    @Inject
    GardenerRepository gardenerRepository;
    @Inject
    AgroalDataSource dataSource;
    @Inject
    Logger logger;

    @Test
    @Transactional
    void createAGardner() {
        Gardener gardener = new Gardener("Max2");
        gardenerRepository.persist(gardener);
        Table table = new Table(dataSource, "Gardener");


        output(table).toConsole();
        assertThat(table).row(0).column("name").value().isEqualTo("Max2");
    }

    @Test
    void getAGardener() {
        createAGardner();
        Gardener gardener = gardenerRepository.findById(1L);
        assertThat(new Table(dataSource, "Gardener")).row(0).column("name")
                .value()
                .isEqualTo(gardener.getName());
    }


}