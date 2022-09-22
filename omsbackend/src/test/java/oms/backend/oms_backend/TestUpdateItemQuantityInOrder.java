package oms.backend.oms_backend;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class TestUpdateItemQuantityInOrder {
    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private static Integer port = 8080;

    private Integer orderId;
    private String upc;
    private Integer updatedQuantity;
    private String url = "http://localhost:";

    private String path = "/api/order/items/";

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = \"http://localhost:\"");
        log.info("Setting port = " + port);
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
    }

    @Test
    void testUpdateQuantityInOrderWithValidOrderID() {
        log.debug("Testing Update Quantity In Order With Valid Order ID");

        orderId = 10;
        log.info("Set order ID = " + orderId);

        upc = "100000111111";
        log.info("Set upc = " + orderId);

        updatedQuantity = 19;
        log.info("Set expected update quantity = " + updatedQuantity);

        response =  when().
                patch("updateItemQuantity/" + String.valueOf(orderId) + "/" + upc + "/" + String.valueOf(updatedQuantity)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();

        String expectedResponseBody = "Item " + upc + " updated to " + updatedQuantity;
        String actualResponseBody = response.getBody().prettyPrint();

        log.info("Then the status code = " + statusCode);
        log.info("Then the response body = " + actualResponseBody);
        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(expectedResponseBody, actualResponseBody);
    }

    @Test
    void testUpdateQuantityInOrderWithInvalidOrderID() {
        log.debug("Testing Update Quantity In Order With Valid Order ID");

        orderId = 9999;
        log.info("Set order ID = " + orderId);

        upc = "100000111111";
        log.info("Set upc = " + orderId);

        updatedQuantity = 19;
        log.info("Set expected update quantity = " + updatedQuantity);

        response =  when().
                patch("updateItemQuantity/" + String.valueOf(orderId) + "/" + upc + "/" + String.valueOf(updatedQuantity)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();

        log.info("Then the status code = " + statusCode);
        Assertions.assertEquals(400, statusCode);
    }
}
