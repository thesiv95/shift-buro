package ftc.shift.sample.services;

import ftc.shift.sample.models.User;
import ftc.shift.sample.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service

public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository cardRepository) {
        this.userRepository = cardRepository;
    }

    public List<User> getAllUsers(){ return this.userRepository.getAllUsers(); }

    public User getUser(Integer id) { return this.userRepository.getUser(id); }

    public User addUsers(User user) { return this.userRepository.addUser(user); }

    public void deleteUSer(Integer userId) { this.userRepository.deleteUser(userId); }

    public void changeBalance(Integer price, Integer recipientId, Integer donorId) {
        this.userRepository.changeBalance(price, recipientId, donorId);
    }
}
