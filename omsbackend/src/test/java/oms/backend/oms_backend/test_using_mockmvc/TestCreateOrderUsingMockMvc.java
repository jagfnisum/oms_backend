package oms.backend.oms_backend.test_using_mockmvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import oms.backend.models.Order;
import oms.backend.models.OrderItems;
import oms.backend.oms_backend.OmsbackendApplication;
import oms.backend.services.OrderService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = OmsbackendApplication.class)
@SpringBootTest
@AutoConfigureMockMvc


public class TestCreateOrderUsingMockMvc {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    OrderService orderService;

    @Test
    public void TestCreateOrder() throws Exception {
        Mockito.when(orderService.createOrder(Mockito.any(Order.class))).thenReturn(true);
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/api/order/createOrder")
                .content("{\n" +
                        "    \"price\": 1000,\n" +
                        "    \"userId\": 5,\n" +
                        "    \"addressID\": 3,\n" +
                        "    \"creditCardID\": 3,\n" +
                        "    \"orderItems\": [\n" +
                        "        {\n" +
                        "            \"quantity\": 1,\n" +
                        "            \"upc\": \"100001111111\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"quantity\": 13,\n" +
                        "            \"upc\": \"100011111111\"\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON);

        mockMvc.perform(builder)
                .andExpect(status().isCreated())
                .andExpect(content().string(Matchers.is("Order created")));

    }
}
