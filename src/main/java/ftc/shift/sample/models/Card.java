package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class Card {
    @ApiModelProperty(value = "ID карточки", required = true)
    private Integer id;

    @ApiModelProperty(value = "Имя", required = true)
    private String name;

    @ApiModelProperty(value = "Телефон", required = true)
    private String phone;

    @ApiModelProperty(value = "Текст карточки", required = true)
    private String task;

    @ApiModelProperty(value = "Актуально или нет", required = true)
    private Boolean isActive;

    public Card(){
    }

    public Card(Integer id, String name, String phone, String task, Boolean isActive){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.task = task;
        this.isActive = isActive;
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

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getTask(){
        return task;
    }

    public void setTask(String Task){
        this.task = task;
    }

    public Boolean getIsActive(){
        return isActive;
    }

    public void setIsActive(Boolean isActive){
        this.isActive = isActive;
    }

}


