package oms.backend.omsbackend;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.when;


@SpringBootTest
public class TestGetOrders {

    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private Integer port = 8080;

    private Integer orderId;
    private String url = "http://localhost:";

    private String path = "/api/order/";

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = \"http://localhost:\"");
        log.info("Setting port = 8080");
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
    }

    @Test
    void testGetWithInvalidOrderId() {
        log.debug("Testing GET Method with invalid order id");
        orderId = 999;
        log.info("Setting order ID = " + orderId);
        response =  when().
                        get("getOrders/" + String.valueOf(orderId)).
                    then().
                        extract().response();
        int statusCode = response.getStatusCode();
        log.info("The the status code = " +statusCode);
        Assertions.assertEquals(400, statusCode);
    }

    @Test
    void testGetOrderById() {
        log.debug("Testing GET Method with valid order id");
        orderId = 1;
        log.info("Setting order ID = " + orderId);
        response =  when().
                        get("getOrders/" + String.valueOf(orderId)).
                    then().
                        extract().response();
        int statusCode = response.getStatusCode();
        log.info("The the status code = " +statusCode);
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    void getAllOrder() {
        log.debug("Testing get all order");
        response =  when().
                get("getOrders/").
                    then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("The the status code = " +statusCode);
        Assertions.assertEquals(200, statusCode);
    }
}
