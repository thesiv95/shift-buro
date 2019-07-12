package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;


public class User {
    @ApiModelProperty(value = "ID пользователя", required = true)
    private Integer id;

    @ApiModelProperty(value = "имя", required = true)
    private String name;

    @ApiModelProperty(value = "ава", required = true)
    private String picUrl;

    @ApiModelProperty(value = "баланс", required = true)
    private Integer balance = 50;

    public User() {

    }

    public User(String name, String picUrl, Integer balance) {
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


