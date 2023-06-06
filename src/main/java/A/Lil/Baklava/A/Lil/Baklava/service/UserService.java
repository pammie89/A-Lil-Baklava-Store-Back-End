package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.User;
import A.Lil.Baklava.A.Lil.Baklava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {this.userRepository = userRepository;}

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        int userId = generateUserId();
        user.setUserId(userId);
        user.put(userId, user);
        return user;
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }


    // Helper method to generate a unique user ID
    private int generateUserId() {
        // Logic to generate a unique ID, such as using a counter or UUID
        // For simplicity, let's assume a counter-based approach
        int maxId = users.keySet().stream().max(Integer::compareTo).orElse(0);
        return maxId + 1;
    }

}
