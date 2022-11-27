package at.htl.boundary;

import at.htl.entities.Gardener;
import at.htl.entities.Invoice;
import at.htl.entities.dto.InvoiceDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
public class InvoiceResourceTest {
    @Test
    @Transactional
    void testThePostEndpoint() {
        Response response = given()
                .header("Content-type", "application/json")
                .body(new Invoice(33, null))
                .when()
                .post("/api/invoice")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    void testTheGetEndpoint() {
        List<InvoiceDTO> invoice = given().when().get("/api/invoice/").then()
                .extract()
                .jsonPath()
                .getList(".", InvoiceDTO.class);

        assertThat(invoice.get(0).getTotalcost()).isEqualTo(22);

    }

    @Test
    void testThePutEndpoint() {

        Response response = given().header("Content-type", "application/json")
                .and()
                .body(new Invoice(300, null))
                .when()
                .put("/api/invoice/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }

    @Test
    void testTheDeleteEndpoint() {
        Response response = given()
                .when()
                .delete("/api/invoice/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }


}
