package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

public class User {
    @ApiModelProperty(value = "Уникальный идентификатор пользователя", required = true)
    private Integer id;

    @ApiModelProperty(value = "ФИО", required = true)
    private String full_name;

    @ApiModelProperty(value = "возраст", required = true)
    private Integer age;

    @ApiModelProperty(value = "город", required = true)
    private String city;

    @ApiModelProperty(value = "аватар клиента", required = true)
    private String pic_url; // ссылка на картинку (либо ее расположение на устройстве)

    @ApiModelProperty(value = "номер телефона", required = true)
    private String phone_number; // формат номера +7XXXXXXXXXX

    @ApiModelProperty(value = "баланс пользователя", required = true)
    private Integer balance;

    @ApiModelProperty(value = "список желаний пользователя", required = true)
    private List<Integer> wishes; // айди желания свзяано с самим желание через бд

    @ApiModelProperty(value = "статус пользователя", required = true)
    private Boolean status; // true = готов помогать, false = не готов помогать

    @ApiModelProperty(value = "описание пользователя", required = true)
    private String discription;

    //конструкторы
    public User() {
        //по умолчанию
    }

    public User(Integer id, String full_name, Integer age, String city, String pic_url,
                String phone_number, Integer balance, List<Integer> wishes,
                Boolean status, String discription) { // "полностью оформленный пользователь"
        this.id = id;
        this.full_name = full_name;
        this.age = age;
        this.city = city;
        this.pic_url = pic_url;
        this.phone_number = phone_number;
        this.balance = balance;
        this.wishes = wishes;
        this.status = status;
        this.discription = discription;
    }

    //
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    //
    public String getFullName() {
        return full_name;
    }

    public void setFullName(String name) {
        this.full_name = name;
    }
    //
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    //
    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }
    //
    public String getPicUrl() { return pic_url; }

    public void SetPicUrl(String pic_url) { this.pic_url = pic_url; }
    //
    public String getPhoneNumber() { return phone_number; }

    public void setPhone_number(String phone_number) { this.phone_number = phone_number; }
    //
    public Integer getBalance() { return balance; }

    public void setBalance(Integer balance) { this.balance = balance; }
    //
    public List<Integer> getWishes() { return wishes; }

    public void setWishes(List<Integer> wishes) { this.wishes = wishes; }
    //
    public Boolean getStatus() { return status; }

    public void setStatus(Boolean status) { this.status = status; }
    //
    public String getDiscription() { return discription; }

    public void setDiscription(String discription) { this.discription = discription; }
    //
}
