package org.codecraftlabs.phoenix;

import org.codecraftlabs.phoenix.service.Product;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductCatalogServiceTests {
    @Autowired
    private MockMvc mvc;

    @Test
    @Order(1)
    public void testCreateProduct() throws Exception {
        this.mvc.perform(post("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getCreateProductData().toString())
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    private JSONObject getCreateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "test-product-1");
        createProductData.put("title", "Test product 001");
        return createProductData;
    }
}
