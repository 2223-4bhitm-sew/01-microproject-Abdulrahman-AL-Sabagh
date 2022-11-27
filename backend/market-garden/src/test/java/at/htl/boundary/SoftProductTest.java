package at.htl.boundary;


import at.htl.entities.SoftProduct;
import at.htl.entities.dto.ProductDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
public class SoftProductTest {


    @Test
    @Transactional
    void testThePostEndpoint() {
        Response response = given()
                .header("Content-type", "application/json")
                .body(new SoftProduct("something", 22))
                .when()
                .post("/api/soft-product")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    void testTheGetEndpoint() {
        List<ProductDTO> product = given().when().get("/api/soft-product/").then()
                .extract()
                .jsonPath()
                .getList(".", ProductDTO.class);
        assertThat(product.get(0).getPrice()).isEqualTo(22);
        assertThat(product.get(0).getName()).isEqualTo("something");

    }

    @Test
    void testThePutEndpoint() {

        Response response = given().header("Content-type", "application/json")
                .and()
                .body(new SoftProduct("something",100))
                .when()
                .put("/api/soft-product/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }

    @Test
    void testTheDeleteEndpoint() {
        Response response = given()
                .when()
                .delete("/api/soft-product/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }

}