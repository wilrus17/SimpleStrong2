package com.will.simplestrong;

public class Routine {

    private int _id;
    private String name;
    private String description;

    public Routine() {
    }

    public Routine(String name, String description) {
        this._id = _id;
        this.name = name;
        this.description = description;
    }



    // Setters

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
