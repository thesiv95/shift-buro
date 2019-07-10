package ftc.shift.sample.api;

import com.sun.org.apache.xpath.internal.operations.Bool;
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


    //метод позволяет получить список всех пользователей, на вход ничего не принимает, на выходе получает List<User>
    @GetMapping(USERS_PATH + "/getAllUsers")
    @ApiOperation(value = "Получение всех пользователей")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> user = service.getAllUsers();
        return ResponseEntity.ok(user);
    }

    //метод позволяет получить пользователя по его айди на вход принимает число - юзер айди, на выходе получает экземпляр класса User
    @GetMapping(USERS_PATH + "/getUser" + "/{id}")
    @ApiOperation(value = "Получение пользователя по айди")
    public ResponseEntity<User> getUser(
            @ApiParam(value = "Идентификатор пользователя")
            @PathVariable Integer id) {
        User user = service.getUser(id);
        return ResponseEntity.ok(user);
    }

    //метод позволяет добавить пользователя, на вход получается экземпляр класса User,
    //возвращает значение true (был создан пользователь) или false (создать не удалось)
    @PostMapping(USERS_PATH + "/addUser")
    @ApiOperation(value = "Добавление нового пользователя")
    public ResponseEntity<String> addUser(
            @ApiParam(value = "Данные для нового пользователя")
            @RequestBody User user) {
        String result = service.addUsers(user);
        return ResponseEntity.ok(result);
    }

    //метода позволяет удалить пользователя, на вход получает число - айди пользователя, котрого надо удалить
    //возвращает переменную типа boolean (0 - не удалось удалить, не 0 - удалось)
    @DeleteMapping(USERS_PATH + "/deleteUser" + "/{userId}")
    @ApiOperation(value = "Удаление пользователя")
    public ResponseEntity<String> deleteUser(
            @ApiParam(value = "Айди для удаления")
            @PathVariable Integer userId) {
        String result = service.deleteUSer(userId);
        return ResponseEntity.ok(result);
    }

    //метод позволяет изменить баланс двух пользователей после выполнения желания, на вход принимает три числа - айди сделки,
    //айди того кто выполняет, айди того кто разметистил желание;
    //возвращает переменную типа boolean (0 - не удалось удалить, не 0 - удалось)
    @PostMapping(USERS_PATH + "/changeBalance")
    @ApiOperation(value = "Изменение баланса при сделке")
    public ResponseEntity<String> changeBalance(
            @ApiParam(value = "Цена сделки")
            @RequestHeader Integer price,
            @ApiParam(value = "Айди получателя")
            @RequestHeader Integer recipientId,
            @ApiParam(value = "Айди донора")
            @RequestHeader Integer donorId) {
        String result = service.changeBalance(price, recipientId, donorId);
        return ResponseEntity.ok(result);
    }

    //метод позволяет изменить инфорацию о пользователе, на вход принимает экземпляр класса User
    //возвращает переменную типа boolean (0 - не удалось обновить, не 0 - удалось)
    @PostMapping(USERS_PATH + "/updateUser")
    @ApiOperation(value = "обновление информации о пользователе")
    public ResponseEntity<String> updateUser(
            @ApiParam(value = "пользователь с новой информацией")
            @RequestBody User user) {
        String result = service.updateUser(user);
        return ResponseEntity.ok(result);
    }
}