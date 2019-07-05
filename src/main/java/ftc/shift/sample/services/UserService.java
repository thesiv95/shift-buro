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

    public List<User> getAllUsers(){
        return this.userRepository.getAllUsers();
    }
}