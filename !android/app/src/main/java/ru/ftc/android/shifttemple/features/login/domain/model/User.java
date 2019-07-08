package ru.ftc.android.shifttemple.features.login.domain.model;

public final class User {

    private Integer id;
    private String full_name;
    private Integer age;
    private String city;
    private String pic_url; // ссылка на картинку (либо ее расположение на устройстве)
    private String phone_number; // формат номера +7XXXXXXXXXX
    private Integer balance;
    private Boolean status; // true = готов помогать, false = не готов помогать
    private String description;

    public User(Integer id, String full_name, Integer age, String city, String pic_url,
                String phone_number, Integer balance, Boolean status, String description) {
        this.id = id;
        this.full_name = full_name;
        this.age = age;
        this.city = city;
        this.pic_url = pic_url;
        this.phone_number = phone_number;
        this.balance = balance;
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
        return full_name;
    }

    public void setFullName(String name) {
        this.full_name = name;
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

    public String getPicUrl() {
        return pic_url;
    }

    public void SetPicUrl(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDiscription(String discription) {
        this.description = description;
    }
}