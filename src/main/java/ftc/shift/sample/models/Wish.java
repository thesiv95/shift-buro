package ftc.shift.sample.models;

import io.swagger.annotations.ApiModelProperty;

public class Wish {
    @ApiModelProperty(value = "Айди желания", required = true)
    private Integer id;

    @ApiModelProperty(value = "Название", required = true)
    private String name;

    @ApiModelProperty(value = "Цена", required = true)
    private Integer price;

    @ApiModelProperty(value = "Описание", required = true)
    private String description;

    //конструкторы
    public Wish() {
        //по умолчанию
    }

    public Wish(Integer id, String name, Integer price, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    //
    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }
    //
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
    //
    public Integer getPrice() { return price; }

    public void setPrice(Integer price) { this.price = price; }
    //
    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }
    //
}