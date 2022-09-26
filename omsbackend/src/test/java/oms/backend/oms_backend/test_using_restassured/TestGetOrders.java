package oms.backend.oms_backend.test_using_restassured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import oms.backend.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static io.restassured.RestAssured.when;


@SpringBootTest
public class TestGetOrders {

    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private static Integer port = 8080;

    private String url = "http://localhost:";

    private String path = "/api/order/";

    private Order order;

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = \"http://localhost:\"");
        log.info("Setting port = " + port);
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
        order = new Order();
    }

    @Test
    void testGetWithInvalidOrderId() {
        log.debug("Testing GET Method with invalid order id");
        Order order = new Order();
        order.setOrderID(999);
        log.info("Setting order ID = " + order.getOrderID());
        response =  when().
                get("getOrders/" + String.valueOf(order.getOrderID())).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);
        Assertions.assertEquals(400, statusCode);
    }

    @Test
    void testGetOrderById() {
        log.debug("Testing GET Method with valid order id");

        order.setOrderID(1);
        log.info("Set order ID = " + order.getOrderID());
        response =  when().
                get("getOrders/" + String.valueOf(order.getOrderID())).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " + statusCode);

        JsonPath jsonPath = response.jsonPath();
        int actualOrderId = jsonPath.get("orderID");

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(order.getOrderID(),actualOrderId);
    }

    @Test
    void getAllOrder() {
        ArrayList<Integer> orderId = new ArrayList<Integer>();
        log.debug("Testing get all order");
        response =  when().
                get("getOrders/").
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);

        JsonPath jsonPath = response.jsonPath();
        orderId = jsonPath.get("orderID");

        log.info("List of order id: " + jsonPath.get("orderID"));
        log.info("List of order status: " + jsonPath.get("orderStatus"));
        // Assert
        Assertions.assertEquals(200, statusCode);
        Assertions.assertTrue(orderId.contains(1));
    }
}
