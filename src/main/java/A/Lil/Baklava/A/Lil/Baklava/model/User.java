package A.Lil.Baklava.A.Lil.Baklava.model;


import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column
    private String name;
    @Column(unique = true)
    private String email;
    @Column
    private String password;

    public User() {
    }


    public User(String name, String email, String password) {

        this.name = name;
        this.email = email;
        this.password = password;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
