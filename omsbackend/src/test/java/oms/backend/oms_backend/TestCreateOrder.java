package oms.backend.oms_backend;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import static io.restassured.RestAssured.when;

@SpringBootTest
public class TestCreateOrder {

    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private Integer port = 8080;

    private String url = "http://localhost:";

    private String path = "/api/order/";

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = http://localhost/");
        log.info("Setting port = 8080");
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
    }

    @Test
    void testCreateOrder() throws JSONException {
        log.debug("Testing Post Method with creating new orders");

        JSONObject newOrder = new JSONObject();

        newOrder.put("price",180.0);
        newOrder.put("orderStatus", null);
        newOrder.put("dateShipped", null);
        newOrder.put("dateDelivered", null);
        newOrder.put("userId", 3);
        newOrder.put("dateOrdered", "2011-12-18 13:17:17");
        newOrder.put("orderID", 36);
        newOrder.put("addressID", 1);
        newOrder.put("creditCardID", 7);
        response =  RestAssured.
                given().
                contentType("application/json").
                body(newOrder.toString()).
                when().
                post("createOrder");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(201, statusCode);
    }



}
