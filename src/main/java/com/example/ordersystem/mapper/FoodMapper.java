package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodMapper {

    /*@Select("select * from food")
    @Results({
            @Result(column = "f_id", property = "id"),
            @Result(column = "f_name", property = "name"),
            @Result(column = "f_materials", property = "material"),
            @Result(column = "f_price", property = "price"),
            @Result(column = "f_type", property = "type"),
            @Result(column = "f_img", property = "img")
    })*/
    List<Food> selectFood();

    boolean insertFood(Food food);

    List<Integer> getF_ids();

    boolean updateFoodById(Food food);

    boolean deleteFoodById(Food food);
}
