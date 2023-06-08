package A.Lil.Baklava.A.Lil.Baklava.seed;

import A.Lil.Baklava.A.Lil.Baklava.model.User;
import A.Lil.Baklava.A.Lil.Baklava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    @Autowired
    public UserDataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user1 = new User("John Doe", "john@example.com", "password123");
            User user2 = new User("Jane Smith", "jane@example.com", "password456");
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }
}
