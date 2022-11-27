package at.htl.boundary;

import at.htl.entities.Gardener;
import at.htl.entities.dto.GardenerDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@QuarkusTest
public class GardenerResourceTest {

    @Test
    @Transactional
    void testThePostEndpoint() {
        Response response = given()
                .header("Content-type", "application/json")
                .body(new Gardener("bob"))
                .when()
                .post("/api/gardener")
                .then()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
    }

    @Test
    void testTheGetEndpoint() {
        List<GardenerDTO> gardener = given().when().get("/api/gardener/").then()
                .extract()
                .jsonPath()
                .getList(".", GardenerDTO.class);
        assertThat(gardener.get(0).getName()).isEqualTo("bob");

    }

    @Test
    void testThePutEndpoint() {
        Response response = given().header("Content-type", "application/json")
                .and()
                .body(new Gardener("MAX"))
                .when()
                .put("/api/gardener/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }

    @Test
    void testTheDeleteEndpoint() {
        Response response = given()
                .when()
                .delete("/api/gardener/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }

}
