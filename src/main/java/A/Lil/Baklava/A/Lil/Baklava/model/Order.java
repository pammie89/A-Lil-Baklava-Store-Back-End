package A.Lil.Baklava.A.Lil.Baklava.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int productId;

    @Column
    private int quantity;

    @Column
    private int userId;



    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    public Order() {}

    /**
     * Constructs an instance of Order with the specified parameters.
     *
     * @param id         The ID of the order.
     * @param productId  The ID of the product in the order.
     * @param quantity   The quantity of the product in the order.
     */
    public Order(Long id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    /**
     * Retrieves the ID of the order.
     *
     * @return The ID of the order.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the order.
     *
     * @param id The ID of the order.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the user ID associated with the order.
     *
     * @return The user ID associated with the order.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the order.
     *
     * @param userId The user ID associated with the order.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Retrieves the product ID of the order.
     *
     * @return The product ID of the order.
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the product ID of the order.
     *
     * @param productId The product ID of the order.
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Retrieves the quantity of the product in the order.
     *
     * @return The quantity of the product in the order.
     */
    public int getQuantity() {
        return quantity;
    }


    /**
     * Sets the quantity of the product in the order.
     *
     * @param quantity The quantity of the product in the order.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
