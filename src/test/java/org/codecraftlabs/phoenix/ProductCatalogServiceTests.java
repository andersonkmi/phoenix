package org.codecraftlabs.phoenix;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    public void testGetProductDetails() throws Exception {
        String productId = getCreateProductData().getString("id");
        this.mvc.perform(get("/product/" + productId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(getCreateProductData().toString()));
    }

    @Test
    @Order(3)
    public void testGetProductList() throws Exception {
        this.mvc.perform(get("/product")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[" + getCreateProductData() + "]"));
    }

    @Test
    @Order(4)
    public void testUpdateProduct() throws Exception {
        this.mvc.perform(put("/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getUpdateProductData().toString()))
                .andExpect(status().isOk());
    }

    private JSONObject getCreateProductData() throws JSONException {
        JSONObject createProductData = new JSONObject();
        createProductData.put("id", "test-product-1");
        createProductData.put("title", "Test product 001");
        return createProductData;
    }

    public JSONObject getUpdateProductData() throws JSONException {
        JSONObject updateProductData = new JSONObject();
        updateProductData.put("id", "test-product-1");
        updateProductData.put("title", "Test product 001 updated title");
        return updateProductData;
    }
}
