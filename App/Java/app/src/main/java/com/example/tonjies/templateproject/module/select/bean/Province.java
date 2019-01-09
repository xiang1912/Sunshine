package com.example.tonjies.templateproject.module.select.bean;

/**
 * Created by 舍长 on 2019/1/5
 * describe:省份实体类
 */
public class Province {
    /**
     * id : 2
     * name : 上海
     */

    private int id;
    private String name;

    public Province(int id, String name) {
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
