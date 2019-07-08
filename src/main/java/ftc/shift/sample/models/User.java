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

    public User(String name, String phone, Integer balance, Integer age,
                String city, String pic_url, String status, String description) {
        //this.id = id;
        this.name = name;
        this.phone = phone;
        this.balance = balance;
        this.age = age;
        this.city = city;
        this.pic_url = pic_url;
        this.status = status;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
