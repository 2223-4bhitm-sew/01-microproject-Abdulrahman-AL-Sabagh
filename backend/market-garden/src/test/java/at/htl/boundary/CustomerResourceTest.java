package at.htl.boundary;


import at.htl.entities.Customer;
import at.htl.entities.dto.CustomerDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@QuarkusTest
class CustomerResourceTest {

    @Inject
    Logger logger;


    @Test
    public void testTheGetEndpoint() {

        List<CustomerDTO> customer = given().when().get("/api/customer")
                .then().statusCode(200)
                .extract().jsonPath().getList(".", CustomerDTO.class);

        assertThat(customer.get(0).getName()).isEqualTo("bob");
    }

    @Test
    public void testGetById() {
        CustomerDTO customer = given().when().get("/api/customer/1")
                .then().statusCode(200)
                .extract().jsonPath().getObject(".", CustomerDTO.class);
        assertThat(customer.getName()).isEqualTo("bob");
    }

    @Test
    @Transactional
    public void testThePostEndpoint() {
        Response response = given().header("Content-type", "application/json")
                .and()
                .body(new Customer("bob"))
                .when().post("/api/customer")
                .then()
                .extract().response();
        logger.info(response.jsonPath());
        logger.info(response.body());

        Assertions.assertEquals(201, response.statusCode());

    }

    @Test
    @Transactional
    public void testThePutEndpoint() {
        Response response = given().header("Content-type", "application/json").and()
                .body(new Customer(1L, "bob"))
                .when()
                .put("/api/customer/1")
                .then()
                .extract().response();
        Assertions.assertEquals(202, response.statusCode());
    }
    @Test
    @Transactional
    public void testTheDeleteEndpoint() {

        Response response = given().when().delete("/api/customer/1").then().extract().response();
        Assertions.assertEquals(202,response.statusCode());

        testThePostEndpoint();
       response = given().queryParam("id", 33).when().delete("/api/customer/33").then().extract().response();
       Assertions.assertEquals(404,response.statusCode());
    }
}