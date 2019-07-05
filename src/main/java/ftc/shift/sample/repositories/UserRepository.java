package ftc.shift.sample.repositories;

import ftc.shift.sample.models.User;

import java.util.List;

/**
 * Интерфейс для получения пользователей
 */


public interface UserRepository {
    List<User> getAllUsers();
}
