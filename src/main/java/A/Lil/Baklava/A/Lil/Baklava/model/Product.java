package A.Lil.Baklava.A.Lil.Baklava.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="product")
public class Product {

    private String name;
    private double price;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();

    /**
     * Constructs an instance of Product with the specified name and price.
     *
     * @param name  The name of the product.
     * @param price The price of the product.
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Product() {
    }

    /**
     * Constructs an instance of Product with the specified ID, name, and price.
     *
     * @param id    The ID of the product.
     * @param name  The name of the product.
     * @param price The price of the product.
     */
    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    /**
     * Retrieves the ID of the product.
     *
     * @return The ID of the product.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the product.
     *
     * @param id The ID of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price The price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }


}
