package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.Food;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("select * from food")
    @Results({
            @Result(column = "f_id", property = "id"),
            @Result(column = "f_name", property = "name"),
            @Result(column = "f_materials", property = "material"),
            @Result(column = "f_price", property = "price"),
            @Result(column = "f_type", property = "type"),
            @Result(column = "f_img", property = "img")

    })
    List<Food> selectFood();

    @Insert("insert into food(f_id, f_name, f_materials, f_price, f_type, f_img) " +
            "values(#{id}, #{name}, #{material}, #{price}, #{type}, #{img})")
    boolean insertFood(Food food);

    @Select("select f_id from food")
    List<Integer> getF_ids();

    @Update("update food set f_name=#{name}, f_materials=#{material}, f_price=#{price}, f_type=#{type}" +
            " where f_id=#{id}")
    boolean updateFoodById(Food food);

    @Delete("delete from food where f_id=#{id}")
    boolean deleteFoodById(Food food);
}
