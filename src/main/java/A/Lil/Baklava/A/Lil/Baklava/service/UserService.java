package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private final Map<Integer, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

    public User createUser(User user) {
        int userId = generateUserId();
        user.setUserId(userId);
        users.put(userId, user);
        return user;
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
