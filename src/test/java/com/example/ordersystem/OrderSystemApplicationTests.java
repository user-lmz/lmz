package com.example.ordersystem;

import com.example.ordersystem.mapper.FoodMapper;
import com.example.ordersystem.pojo.Food;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class OrderSystemApplicationTests {
    @Autowired
    private FoodMapper foodMapper;

    @Test
    void contextLoads() {
        List<Food> foods=foodMapper.selectFood();
        for (Food food: foods) {
            System.out.println(food.getId()+" , "+food.getName()+" , "+
            food.getMaterial()+" , "+food.getPrice()+" , "+food.getType());
        }
//        List<Integer> f_ids = foodMapper.getF_ids();
//        for (int id: f_ids) {
//            System.out.println(id);
//        }
    }

}
