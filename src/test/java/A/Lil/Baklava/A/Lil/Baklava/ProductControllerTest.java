package A.Lil.Baklava.A.Lil.Baklava;


import A.Lil.Baklava.A.Lil.Baklava.controller.ProductController;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getAllProducts_ReturnsListOfProducts() throws Exception {
        Product product1 = new Product(1, "Product 1", 10.99);
        Product product2 = new Product(2, "Product 2", 19.99);
        List<Product> products = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Product 1"))
                .andExpect(jsonPath("$[0].price").value(10.99))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Product 2"))
                .andExpect(jsonPath("$[1].price").value(19.99));

        verify(productService, times(1)).getAllProducts();
        verifyNoMoreInteractions(productService);
    }
    @Test
    public void getProductById_WithValidId_ReturnsProduct() throws Exception {
        Product product = new Product(1, "Product 1", 10.99);

        when(productService.getProductById(anyInt())).thenReturn(product);

        mockMvc.perform(get("/products/{productId}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.price").value(10.99));

        verify(productService, times(1)).getProductById(1);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void addProduct_WithValidProduct_ReturnsAddedProduct() throws Exception {
        Product product = new Product(1, "Product 1", 10.99);

        when(productService.addProduct(any(Product.class))).thenReturn(product);

        mockMvc.perform(post("/products")
                        .contentType("application/json")
                        .content("{\"id\": 1, \"name\": \"Product 1\", \"price\": 10.99}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Product 1"))
                .andExpect(jsonPath("$.price").value(10.99));

        verify(productService, times(1)).addProduct(any(Product.class));
        verifyNoMoreInteractions(productService);
    }

//    @Test
//    public void updateProduct_WithValidId_ReturnsUpdatedProduct() throws Exception {
//        Product product = new Product(1, "Product 1", 10.99);
//        Product updatedProduct = new Product(1, "Updated Product 1", 12.99);
//
//        when(productService.updateProduct(anyInt(), any(Product.class)))
//                .thenAnswer(invocation -> {
//                    int id = invocation.getArgument(0);
//                    Product productArg = invocation.getArgument(1);
//                    if (id == productArg.getId()) {
//                        return updatedProduct;
//                    } else {
//                        throw new IllegalArgumentException("Invalid arguments");
//                    }
//                });
//
//        mockMvc.perform(put("/products/{id}", 1)
//                        .contentType("application/json")
//                        .content("{\"id\": 1, \"name\": \"Updated Product 1\", \"price\": 12.99}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("Updated Product 1"))
//                .andExpect(jsonPath("$.price").value(12.99));
//
//        verify(productService, times(1)).updateProduct(1, updatedProduct);
//        verifyNoMoreInteractions(productService);
//    }



    @Test
    public void deleteProduct_WithValidId_ReturnsNoContent() throws Exception {
        mockMvc.perform(delete("/products/{id}", 1))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));

        verify(productService, times(1)).deleteProduct(1);
        verifyNoMoreInteractions(productService);
    }

//    @Test
//    public void deleteProduct_WithValidId_ReturnsNoContent() throws Exception {
//        // Mock the ProductService to throw an exception when deleteProduct is called
//        doThrow(InformationNotFoundException.class).when(productService).deleteProduct(1);
//
//        mockMvc.perform(delete("/products/{id}", 1))
//                .andExpect(status().isNoContent())
//                .andExpect(content().string(""));
//
//        verify(productService, times(1)).deleteProduct(1);
//        verifyNoMoreInteractions(productService);
//    }


}