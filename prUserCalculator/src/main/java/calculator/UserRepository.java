package calculator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private Map<Integer, User> users = new HashMap<>();

    public User findById(int id) {
        return users.get(id);
    }

    public void save(User user) {
        users.put(user.getId(), user);
    }

    public void delete(int id) {
        users.remove(id);
    }

    public List<User> findAll() {
        List<User> users1 = (List<User>) users;
        return users1;
    }
}

