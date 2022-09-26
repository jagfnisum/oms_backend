package oms.backend.oms_backend.test_using_restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Must run test in order **/
@SpringBootTest
public class TestUpdateStatus {

    private static Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrders.class);

    private  Integer port = 8080;

    private  String url = "http://localhost:";

    private  String path = "/api/order/";

    @BeforeAll
    public static void setUp() throws JSONException {
        log.info("Setting url = http://localhost/");
        log.info("Setting port = 8080");
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
    }

    @Test
    public void TestShippedValid() throws JSONException{
        //pending -> shipped
        log.debug("Testing GET Method with invalid order id");
        int orderId = 9;
        log.info("Shipping order ID = " + orderId);
        response = RestAssured.given().
                when().
                put("updateOrder/shipped/" + String.valueOf(orderId)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);
        Assertions.assertEquals(200, statusCode);
    }

    @Test
    public  void TestCancelledValid() throws JSONException{
        //pending ->cancelled
        log.debug("Testing GET Method with invalid order id");
        int orderId = 10;
        log.info("Cancelling order ID = " + orderId);

        Response response = RestAssured.given().
                when().
                put("updateOrder/cancelled/" + String.valueOf(orderId)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);
        Assertions.assertEquals(200, statusCode);
    }

//    @Test
//    public  void TestDeliveredValid() throws JSONException{
//        //shipped -> delivered
//        log.debug("Testing GET Method with invalid order id");
//        int orderId = 34;
//
//        log.info("delivering order ID = " + orderId);
//        Response response = RestAssured.given().
//                when().
//                put("updateOrder/delivered/" + String.valueOf(orderId)).
//                then().
//                extract().response();
//        int statusCode = response.getStatusCode();
//        log.info("Then the status code = " +statusCode);
//        Assertions.assertEquals(200, statusCode);
//    }

    @Test
    public  void TestShippedInvalid() throws JSONException{
        //cancelled -> shipped (fail)
        log.debug("Testing GET Method with invalid order id");
        int orderId = 10;

        log.info("Shipping order ID = " + orderId);
        Response response = RestAssured.given().
                when().
                put("updateOrder/shipped/" + String.valueOf(orderId)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);
        Assertions.assertEquals(400, statusCode);
    }

//    @Test
//    public  void TestDeliveredInvalid() throws JSONException{
//        //pending -> delivered(fail)
//        log.debug("Testing GET Method with invalid order id");
//        int orderId = 9;
//
//        log.info("Delivering order ID = " + orderId);
//        Response response = RestAssured.given().
//                when().
//                put("updateOrder/delivered/" + String.valueOf(orderId)).
//                then().
//                extract().response();
//        int statusCode = response.getStatusCode();
//        log.info("Then the status code = " +statusCode);
//        Assertions.assertEquals(400, statusCode);
//    }

    @Test
    public  void TestCancelInvalid() throws JSONException{

        log.debug("Testing GET Method with invalid order id");
        int orderId = 9;

        RestAssured.given().
                when().
                put("updateOrder/shipped/" + String.valueOf(orderId));

        log.info("Cancelling order ID = " + orderId);
        Response response = RestAssured.given().
                when().
                put("updateOrder/cancelled/" + String.valueOf(orderId)).
                then().
                extract().response();
        int statusCode = response.getStatusCode();
        log.info("Then the status code = " +statusCode);
        Assertions.assertEquals(400, statusCode);
    }

}
