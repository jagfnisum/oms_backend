package oms.backend.oms_backend;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import oms.backend.models.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.when;

public class TestGetOrderOfUserId {

    private Response response;
    private final static Logger log = LogManager.getLogger(TestGetOrderOfUserId.class);

    private static Integer port = 8080;
    private String url = "http://localhost:";

    private String path = "/api/order/";

    private static Order order;

    @BeforeAll
    public static void setUp() {
        log.info("Setting url = \"http://localhost:\"");
        log.info("Setting port = " + port);
        order = new Order();
    }

    @BeforeEach
    void setupUrl() {
        RestAssured.baseURI = url + port;
        RestAssured.basePath = path;
    }

    @Test
    void testGetOrderOfValidUserId() throws JSONException {
        log.debug("Testing Get Order Of Valid User Id");

        order.setUserId(1);
        log.info("Set userID = " + order.getUserId());

        response =  when().
                get("getOrders/user/" + order.getUserId()).
                then().
                extract().response();

        JSONArray orderList = new JSONArray(response.getBody().prettyPrint());

        int statusCode = response.getStatusCode();
        log.info("Then the status code = " + statusCode);
        Assertions.assertEquals(200, statusCode);

        log.info("Then the response body is not empty");
        Assertions.assertTrue(orderList.length() > 0);

    }

    @Test
    void testGetOrderOfInvalidUserId() throws JSONException {
        log.debug("Testing Get Order Of Invalid User Id");

        order.setUserId(999);
        log.info("Set userID = " + order.getUserId());

        response =  when().
                get("getOrders/user/" + order.getUserId()).
                then().
                extract().response();

        int statusCode = response.getStatusCode();
        log.info("Then the status code = " + statusCode);
        Assertions.assertEquals(404, statusCode);


    }
}

