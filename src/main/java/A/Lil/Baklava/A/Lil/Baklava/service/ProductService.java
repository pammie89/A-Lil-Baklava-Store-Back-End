package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.exception.InformationInvalidException;
import A.Lil.Baklava.A.Lil.Baklava.exception.InformationNotFoundException;
import A.Lil.Baklava.A.Lil.Baklava.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final Map<Integer, Product> products;

    public ProductService() {
        this.products = new HashMap<>();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public Product getProductById(int id) {
        return products.get(id);
    }

    public Product addProduct(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    public Product updateProduct(int id, Product updatedProduct) {
        Product product = products.get(id);
        if (product != null) {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            products.put(id, product);
        }
        return product;
    }

//    public void deleteProduct(int id) {
//        products.remove(id);
//    }

//    public void deleteProduct(int id) {
//
//        products.remove(id);
//        // Optionally, you can explicitly set the response status as 204 No Content
//        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
//        if (response != null) {
//            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
//        } else {
//            // Log or handle the case when response is null
//
//        }
//    }

    public void deleteProduct(int id) {
        Product removedProduct = products.remove(id);
        if (removedProduct != null) {
            // Optionally, you can explicitly set the response status as 204 No Content
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            if (response != null) {
                response.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }
            else {
                // Log or handle the case when response is null
                throw new InformationInvalidException("Please provide a valid product: " + id);
            }

        } else {
            // Handle the case when the product with the given ID is not found
            throw new InformationNotFoundException("Product not found with ID: " + id);
        }

    }
}