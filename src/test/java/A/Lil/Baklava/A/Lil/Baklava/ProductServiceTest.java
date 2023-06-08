package A.Lil.Baklava.A.Lil.Baklava;

import A.Lil.Baklava.A.Lil.Baklava.controller.ProductController;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import A.Lil.Baklava.A.Lil.Baklava.repository.ProductRepository;
import A.Lil.Baklava.A.Lil.Baklava.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
    public void testGetAllProducts() {
        // Arrange
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1, "Product 1", 10.99));
        expectedProducts.add(new Product(2, "Product 2", 15.99));
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Act
        List<Product> actualProducts = productService.getAllProducts();

        // Assert
        assertNotNull(actualProducts);
        assertEquals(expectedProducts.size(), actualProducts.size());

        // Compare each product
        for (int i = 0; i < expectedProducts.size(); i++) {
            Product expectedProduct = expectedProducts.get(i);
            Product actualProduct = actualProducts.get(i);
            assertEquals(expectedProduct.getId(), actualProduct.getId());
            assertEquals(expectedProduct.getName(), actualProduct.getName());
            assertEquals(expectedProduct.getPrice(), actualProduct.getPrice(), 0.01); // Use delta for floating-point numbers
        }
        // Add more assertions as needed
    }


    @Test
    public void testGetProductById() {
        // Arrange
        int productId = 1;
        Product expectedProduct = new Product(productId, "Test Product", 10.99);
        when(productRepository.findById(productId)).thenReturn(Optional.of(expectedProduct));

        // Act
        Product actualProduct = productService.getProductById(productId);

        // Assert
        assertNotNull(actualProduct);
        assertEquals(expectedProduct.getId(), actualProduct.getId());
        assertEquals(expectedProduct.getName(), actualProduct.getName());
        assertEquals(expectedProduct.getPrice(), actualProduct.getPrice(), 0.01); // Use delta for floating-point numbers
        // Add more assertions as needed
    }



    @Test
    public void testAddProduct() {
        // Arrange
        Product newProduct = new Product("New Product", 19.99);
        when(productRepository.save(newProduct)).thenReturn(newProduct);

        // Act
        Product addedProduct = productService.addProduct(newProduct);

        // Assert
        assertNotNull(addedProduct);
        assertEquals(newProduct.getName(), addedProduct.getName());
        assertEquals(newProduct.getPrice(), addedProduct.getPrice(), 0.01); // Use delta for floating-point numbers

        // Verify that the save method was called on the productRepository
        verify(productRepository).save(newProduct);
    }



    @Test
    public void testDeleteProduct() {
        // Arrange
        int productId = 1;
        Product product = new Product("Product to delete", 9.99);
//        when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        // Act
        productService.deleteProduct(productId);

        // Assert
        verify(productRepository).deleteProductById(productId);
    }




}