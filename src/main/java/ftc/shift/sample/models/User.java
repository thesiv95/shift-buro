// Parameter 0 of constructor in ftc.shift.sample.services.CardService required a bean
// of type 'ftc.shift.sample.repositories.CardRepository' that could not be found.
// ГДЕ УКАЗАТЬ?


package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(value = "ID пользователя", required = true)
    private Integer id;

    @ApiModelProperty(value = "Имя пользователя", required = true)
    private String name;

    @ApiModelProperty(value = "Телефон пользователя", required = true)
    private String phone;

    @ApiModelProperty(value = "Кармические очки пользователя", required = true)
    private Integer balance;

    @ApiModelProperty(value = "Возраст пользователя", required = true)
    private Integer age;

    @ApiModelProperty(value = "Город пользователя", required = true)
    private String city;

    @ApiModelProperty(value = "Ссылка на аватар пользователя", required = true)
    private String pic_url;

    @ApiModelProperty(value = "Статус пользователя", required = true)
    private String status;

    @ApiModelProperty(value = "Подробная инфа о пользователе", required = true)
    private String description;


    public User() {
    }

    public User(Integer id, String name, String phone, Integer balance, Integer age,
                String city, String pic_url, String status, String description) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.age = age;
        this.city = city;
        this.pic_url = pic_url;
        this.status = status;
        this.description = description;
    }
}
