package ru.ftc.android.shifttemple.features.cards.domain.model;

public class Card {
    private Integer id;
    private Integer ownerId;
    private String ownerName;
    private String type;
    private String task;
    private String description;
    private Boolean status;
    private Integer price;
    private String city;
    private String phone;

    public Card() {

    }

    public Card(Integer ownerId, String ownerName, String type, String task, String description,
                Boolean status, Integer price, String city, String phone) {
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.type = type;
        this.task = task;
        this.description = description;
        this.status = status;
        this.price = price;
        this.city = city;
        this.phone = phone;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getOwnerId() { return ownerId; }
    public void setOwnerId(Integer ownerId) { this.ownerId = ownerId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }

    public Integer getPrice() { return price; }
    public void setPrice(Integer price) { this.price = price; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getOwnerName() { return ownerName; }
    public void setOwnerName(String ownerName) { this.ownerName = ownerName; }
}


