package ru.ftc.android.shifttemple.features.login.domain.model;

public class User {
    private Integer id;
    private String name;
    private String picUrl;
    private Integer balance;

    public User() {

    }

    public User(String name, String picUrl, Integer balance) {
        this.id = 1;
        this.name = name;
        this.picUrl = picUrl;
        this.balance = balance;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPicUrl() { return picUrl; }
    public void setPicUrl(String picUrl) { this.picUrl = picUrl; }

    public Integer getBalance() { return balance; }
    public void setBalance(Integer balance) { this.balance = balance; }
}


