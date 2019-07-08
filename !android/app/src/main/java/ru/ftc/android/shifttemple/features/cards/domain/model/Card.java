
package ru.ftc.android.shifttemple.features.cards.domain.model;

/**
 * Created: samokryl
 * Date: 01.07.18
 * Time: 22:40
 */

public final class Card {

    private String id;
    private String name;
    private String phone;
    private String task;
    private boolean isActive;

    public Card(String name, String phone, String task) {
        this.name = name;
        this.phone = phone;
        this.task = task;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getTask() {
        return task;
    }

    public boolean isActive() {
        return isActive;
    }
}