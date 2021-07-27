package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodMapper {

    List<Food> selectFood();

    boolean insertFood(Food food);

    List<Integer> getF_ids();

    boolean updateFoodById(Food food);

    boolean deleteFoodById(Food food);
}
