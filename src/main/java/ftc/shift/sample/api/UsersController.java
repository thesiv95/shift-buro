package ftc.shift.sample.api;

import ftc.shift.sample.models.Card;
import ftc.shift.sample.models.User;
import ftc.shift.sample.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @GetMapping(USERS_PATH + "/{id}")
    @ApiOperation(value = "Получение пользователя по айди")
    public ResponseEntity<User> getUser(
            //@ApiParam(value = "Идентификатор пользователя")
            //@RequestHeader("userId") String userId,
            @ApiParam(value = "Идентификатор пользователя")
            @PathVariable Integer id) {
        User user = service.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping(USERS_PATH)
    @ApiOperation(value = "Добавление нового пользователя")
    public ResponseEntity<User> addUser(
            @ApiParam(value = "Данные для нового пользователя")
            @RequestBody User user) {
        User result = service.addUsers(user);
        return ResponseEntity.ok(result);
    }
}