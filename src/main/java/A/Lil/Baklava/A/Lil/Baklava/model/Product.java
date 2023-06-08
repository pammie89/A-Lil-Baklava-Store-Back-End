package A.Lil.Baklava.A.Lil.Baklava.model;

import javax.persistence.*;

@Entity
@Table(name="product")
public class Product {

    private String name;
    private double price;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
//    private String name;
//
//    @Column
//    private double price;

//    @ManyToMany(mappedBy = "products")
//    private List<Order> orders = new ArrayList<>();


    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Product() {
    }

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

//    @Override
//    public String toString() {
//        return "Product{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", orders=" + orders +
//                '}';
//    }
}
