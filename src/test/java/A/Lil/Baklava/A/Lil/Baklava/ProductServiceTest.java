package A.Lil.Baklava.A.Lil.Baklava;

import A.Lil.Baklava.A.Lil.Baklava.controller.ProductController;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.repository.ProductRepository;
import A.Lil.Baklava.A.Lil.Baklava.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ProductController(productService)).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> productList = Arrays.asList(
                new Product(1, "Product 1", 10.0),
                new Product(2, "Product 2", 20.0)
        );

        Mockito.when(productRepository.getAllProducts()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("Product 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", is(10.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("Product 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", is(20.0)));

        Mockito.verify(productRepository, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product(1, "Product 1", 10.0);

        Mockito.when(productRepository.getProductById(Mockito.anyInt())).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Product 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(10.0)));

        Mockito.verify(productRepository, Mockito.times(1)).getProductById(Mockito.anyInt());
    }

    @Test
    public void testAddProduct() throws Exception {
        Product productToAdd = new Product(1, "New Product", 15.0);
        Product addedProduct = new Product(1, "New Product", 15.0);

        Mockito.when(productRepository.addProduct(Mockito.any(Product.class))).thenReturn(addedProduct);

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(productToAdd)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("New Product")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(15.0)));

        Mockito.verify(productRepository, Mockito.times(1)).addProduct(Mockito.any(Product.class));
    }

//    @Test
//    public void testUpdateProduct() throws Exception {
//        int productId = 1;
//        Product updatedProduct = new Product(1, "Updated Product", 25.0);
//        Product productBeforeUpdate = new Product(1, "Product 1", 20.0);
//
//        Mockito.when(productRepository.getProductById(productId)).thenReturn(productBeforeUpdate);
//        Mockito.when(productRepository.updateProduct(productId, updatedProduct)).thenReturn(updatedProduct);
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/products/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(updatedProduct)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is("1")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Updated Product")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.price", is(25.0)));
//
//        Mockito.verify(productRepository, Mockito.times(1)).getProductById(productId);
//        Mockito.verify(productRepository, Mockito.times(1)).updateProduct(productId, updatedProduct);
//    }

    @Test
    public void testDeleteProduct() throws Exception {
        int productId = 1;

        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(productRepository, Mockito.times(1)).deleteProduct(productId);
    }

    private String asJsonString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}