package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;

import java.util.List;

/**
 * Интерфейс для получения пользователей
 */


public interface UserRepository {
    List<User> getAllUsers();
    User addUser(User user);
    void initialize();
    void changeBalance(Integer price, Integer recipientId, Integer donorId);
    User getUser(Integer id);
    void deleteUser(Integer userId);
}
