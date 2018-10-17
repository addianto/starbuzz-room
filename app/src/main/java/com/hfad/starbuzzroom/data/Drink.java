package com.hfad.starbuzzroom.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Drink {

    @PrimaryKey(autoGenerate = true)
    private final int id;

    private String name;
    private String description;
    private int imageResourceId;

    public Drink(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    @Override
    public String toString() {
        return name;
    }
}
