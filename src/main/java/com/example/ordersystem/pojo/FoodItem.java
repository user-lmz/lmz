package com.example.ordersystem.pojo;

import java.util.List;

public class FoodItem {
    private String type;
    private List<FoodList> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<FoodList> getList() {
        return list;
    }

    public void setList(List<FoodList> list) {
        this.list = list;
    }

    public void addList(FoodList foodList) {
        this.list.add(foodList);
    }
}
