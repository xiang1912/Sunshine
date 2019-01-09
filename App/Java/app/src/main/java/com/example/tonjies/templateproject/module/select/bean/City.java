package com.example.tonjies.templateproject.module.select.bean;

/**
 * Created by 舍长 on 2019/1/5
 * describe:
 */
public class City {

    /**
     * id : 205
     * name : 广州
     */

    private int id;
    private String name;

    public City(int id, String name) {
        this.id = id;
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
}
