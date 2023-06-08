package A.Lil.Baklava.A.Lil.Baklava.model;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
//    private int userId;
//
//    @Column
//    private int productId;

    @Column
    private int quantity;



//    @ManyToMany
//    @JoinTable(
//            name = "order_product",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id")
//    )
//    private List<Product> products = new ArrayList<>();



}
