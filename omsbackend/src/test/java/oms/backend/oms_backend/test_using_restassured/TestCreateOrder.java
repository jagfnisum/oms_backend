package oms.backend.oms_backend.test_using_restassured;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import oms.backend.models.Order;
import oms.backend.models.OrderItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


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

        JSONObject newOrder = new JSONObject();
        JSONObject orderItem1 = new JSONObject();
        JSONObject orderItem2 = new JSONObject();

        newOrder.put("price", 1000);
        newOrder.put("userId", 4);
        newOrder.put("addressID", 4);
        newOrder.put("creditCardID", 4);
        
        orderItem1.put("quantity", 20);
        orderItem1.put("upc", "100001111111");

        orderItem2.put("quantity", 30);
        orderItem2.put("upc", "100011111111");
        newOrder.put("orderItems", new JSONObject[]{orderItem1, orderItem2});


        response =  RestAssured.
                given().
                contentType("application/json").
                body(newOrder.toString()).
                when().
                post("/createOrder");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(201, statusCode);
    }
}
