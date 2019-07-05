package ftc.shift.sample.repositories;

import io.swagger.annotations.ApiModelProperty;

public class User {
    @ApiModelProperty(value = "ID пользователя", required = true)
    private Integer id;

    @ApiModelProperty(value = "Имя пользователя", required = true)
    private String name;



    public User(){
    }

    public User(Integer id, String name){
        this.id = id;
        this.name = name;
    }
}
