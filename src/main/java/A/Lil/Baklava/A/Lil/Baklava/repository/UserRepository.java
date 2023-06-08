package A.Lil.Baklava.A.Lil.Baklava.repository;

import A.Lil.Baklava.A.Lil.Baklava.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // You can add custom query methods here if needed
}
