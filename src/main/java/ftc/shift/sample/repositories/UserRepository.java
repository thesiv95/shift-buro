package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;

import java.util.List;

/**
 * Интерфейс для получения пользователей
 */


public interface UserRepository {
    List<User> getAllUsers();
    void addUser(User user);
    void initialize();
    void changeBalance(Integer cardId, Integer userId);
    User getUser(Integer id);
    void deleteUser(Integer userId);
    void updateUser(User user);
}
