package oms.backend.oms_backend.test_using_restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import oms.backend.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestCreateOrder {

    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private Integer port = 8080;

    private String url = "http://localhost:";

    private String path = "/api/order/";

    private ObjectMapper objectMapper;

    private Order order;

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = http://localhost/");
        log.info("Setting port = 8080");
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
        order = new Order();
    }

    @Test
    void testCreateOrder() throws Exception {
        log.debug("Testing Post Method with creating new orders");

        order.setPrice(180);
        order.setOrderStatus("Pending");
        order.setDateShipped("2011-12-18 13:17:17");
        order.setUserId(3);
        order.setDateOrdered("2011-12-18 13:17:17");
        order.setOrderID(23);
        order.setAddressID(1);
        order.setCreditCardID(7);

        objectMapper = new ObjectMapper();
        String newOrder = objectMapper.writeValueAsString(order);
        System.out.println(newOrder);

        response =  RestAssured.
                given().
                contentType("application/json").
                body(newOrder).
                when().
                post("createOrder");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(201, statusCode);
    }



}