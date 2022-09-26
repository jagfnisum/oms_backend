package oms.backend.oms_backend.test_using_restassured;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import oms.backend.models.OrderItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestInsertNewOrderItem {
    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private Integer port = 8080;

    private String url = "http://localhost:";

    private String path = "/api/order/items";

    private ObjectMapper objectMapper;

    private OrderItems orderItem;

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = http://localhost/");
        log.info("Setting port = 8080");
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
        orderItem = new OrderItems();
    }

    @Test
    void testCreateOrderItem() throws Exception {
        log.debug("Testing Post Method with creating new orders");

        orderItem.setOrderid(6);
        orderItem.setQuantity(16);
        orderItem.setOrderItemid(208);
        orderItem.setUpc("600000000002");


        objectMapper = new ObjectMapper();
        String newOrderItem = objectMapper.writeValueAsString(orderItem);
        System.out.println(newOrderItem);

        response = RestAssured.
                given().
                contentType("application/json").
                body(newOrderItem).
                when().
                post("insert");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(201, statusCode);
        System.out.println(response.jsonPath().get("orderid").toString());
        log.info("The the orderid = " + response.jsonPath().get("orderid").toString());
        Assertions.assertEquals(new Integer(6), response.jsonPath().get("orderid"));
        log.info("The the quantity = " + response.jsonPath().get("quantity").toString());
        Assertions.assertEquals(new Integer(16), response.jsonPath().get("quantity"));
        log.info("The the orderItemid = " + response.jsonPath().get("orderItemid").toString());
        Assertions.assertEquals(new Integer(208), response.jsonPath().get("orderItemid"));
        log.info("The the upc = " + response.jsonPath().get("upc"));
        Assertions.assertEquals("600000000002", response.jsonPath().get("upc"));
    }

    @Test
    void testCreateOrderItemWithInvalidOrder() throws Exception {
        log.debug("Testing Post Method with creating new orders");

        orderItem.setOrderid(110000);
        orderItem.setQuantity(16);
        orderItem.setOrderItemid(209);
        orderItem.setUpc("600000000002");


        objectMapper = new ObjectMapper();
        String newOrderItem = objectMapper.writeValueAsString(orderItem);
        System.out.println(newOrderItem);

        response = RestAssured.
                given().
                contentType("application/json").
                body(newOrderItem).
                when().
                post("insert");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(500, statusCode);
    }

    @Test
    void testCreateOrderItemWithInvalidUpc() throws Exception {
        log.debug("Testing Post Method with creating new orders");

        orderItem.setOrderid(1);
        orderItem.setQuantity(16);
        orderItem.setOrderItemid(210);
        orderItem.setUpc("600000000001");


        objectMapper = new ObjectMapper();
        String newOrderItem = objectMapper.writeValueAsString(orderItem);
        System.out.println(newOrderItem);

        response = RestAssured.
                given().
                contentType("application/json").
                body(newOrderItem).
                when().
                post("insert");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(500, statusCode);
    }


    @Test
    void testCreateOrderItemWithExistingOrderItemid() throws Exception {
        log.debug("Testing Post Method with creating new orders");

        orderItem.setOrderid(1);
        orderItem.setQuantity(16);
        orderItem.setOrderItemid(1);
        orderItem.setUpc("600000000002");


        objectMapper = new ObjectMapper();
        String newOrderItem = objectMapper.writeValueAsString(orderItem);
        System.out.println(newOrderItem);

        response = RestAssured.
                given().
                contentType("application/json").
                body(newOrderItem).
                when().
                post("insert");

        log.info("Print response body");
        System.out.println(response.body().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("The the status code = " + statusCode);
        Assertions.assertEquals(400, statusCode);
    }

}
