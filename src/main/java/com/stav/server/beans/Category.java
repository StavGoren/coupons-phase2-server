package com.stav.server.beans;

public class Category {
    private int id;
    private String name;


    public Category(){

    }

    public Category(int id, String name) {
        this(name);
        this.id = id;
    }
    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoriesController{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}' + '\n';
    }
}
