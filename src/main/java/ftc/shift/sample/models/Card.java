package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class Card {
    @ApiModelProperty(value = "ID карточки", required = true)
    private Integer id;

    @ApiModelProperty(value = "ID пользователя", required = true)
    private Integer userId;

    @ApiModelProperty(value = "Текст карточки", required = true)
    private String task;

    @ApiModelProperty(value = "Актуально или нет", required = true)
    private Boolean isActive;

    public Card(){
    }

    public Card(Integer id, Integer userId, String task, Boolean isActive){
        this.id = id;
        this.userId = userId;
        this.task = task;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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


