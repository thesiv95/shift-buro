package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;

import java.util.List;

/**
 * Интерфейс для получения пользователей
 */


public interface UserRepository {
    List<User> getAllUsers();
    String addUser(User user);
    void initialize();
    String changeBalance(Integer price, Integer recipientId, Integer donorId);
    User getUser(Integer id);
    String deleteUser(Integer userId);
    String updateUser(User user);
}
