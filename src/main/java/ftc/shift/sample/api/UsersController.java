package ftc.shift.sample.api;

import ftc.shift.sample.models.User;
import ftc.shift.sample.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(description = "Запросы для работы с карточками желаний")
public class UsersController {
    private static final String USERS_PATH = "/users";

    @Autowired
    private UserService service;

    @GetMapping(USERS_PATH)
    @ApiOperation(value = "Получение всех пользователей")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = service.getAllUsers();
        return ResponseEntity.ok(user);
    }


}