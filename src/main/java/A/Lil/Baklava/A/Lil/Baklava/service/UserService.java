package A.Lil.Baklava.A.Lil.Baklava.service;

import A.Lil.Baklava.A.Lil.Baklava.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Map<Integer, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User getUserById(int userId) {
        return users.get(userId);
    }

}
