package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.User;
import A.Lil.Baklava.A.Lil.Baklava.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        int userId = generateUserId();
        user.setUserId(userId);
        userRepository.save(user);
        return user;
    }

    public User getUserById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Helper method to generate a unique user ID
    private int generateUserId() {
        // Logic to generate a unique ID, such as using a counter or UUID
        // For simplicity, let's assume a counter-based approach
        List<User> allUsers = userRepository.findAll();
        int maxId = allUsers.stream().mapToInt(User::getUserId).max().orElse(0);
        return maxId + 1;
    }

}
