package com.example.ordersystem.service;

import com.example.ordersystem.mapper.FoodMapper;
import com.example.ordersystem.pojo.Food;
import com.example.ordersystem.pojo.FoodItem;
import com.example.ordersystem.pojo.FoodList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FoodService {
    @Resource
    private FoodMapper foodMapper;

    public List<Food> selectFood(){
        return foodMapper.selectFood();
    }

    public boolean insertFood(Food food){
        return foodMapper.insertFood(food);
    }

    public List<Integer> getF_ids(){
        return foodMapper.getF_ids();
    }

    public boolean updateFoodById(Food food){
        return foodMapper.updateFoodById(food);
    }

    public boolean deleteFoodById(Food food){
        return foodMapper.deleteFoodById(food);
    }

    public List<FoodItem> getFoodModel(){
        List<Food> foods = foodMapper.selectFood();
        List<FoodItem> foodItems = new ArrayList<>();
        Map<String, Integer> maps = new HashMap<>();
        int k = 0;
        if (foods != null){
            List<FoodList> foodLists = new ArrayList<>();
            FoodItem foodItem = new FoodItem();
            FoodList foodList = new FoodList();
            foodList.setTitle(foods.get(0).getName());
            foodList.setMaterial(foods.get(0).getMaterial());
            foodList.setPrice(foods.get(0).getPrice());
            foodList.setText(1);
            foodList.setMoney(foods.get(0).getPrice());
            foodList.setImg(foods.get(0).getImg());
            foodList.setF_id(foods.get(0).getId());
            foodLists.add(foodList);

            foodItem.setType(foods.get(0).getType());
            maps.put(foods.get(0).getType(), 0);
            foodItem.setList(foodLists);
            foodItems.add(foodItem);
        }
        for (int i=1; i<foods.size(); i++){
            FoodList foodList = new FoodList();
            foodList.setTitle(foods.get(i).getName());
            foodList.setMaterial(foods.get(i).getMaterial());
            foodList.setPrice(foods.get(i).getPrice());
            foodList.setText(1);
            foodList.setMoney(foods.get(i).getPrice());
            foodList.setImg(foods.get(i).getImg());
            foodList.setF_id(foods.get(i).getId());
            if (maps.containsKey(foods.get(i).getType())){
                int now = maps.get(foods.get(i).getType());
                foodItems.get(now).getList().add(foodList);
                //System.out.println(now+" "+foods.get(i).getName());
            } else {
                List<FoodList> foodLists = new ArrayList<>();
                FoodItem foodItem = new FoodItem();
                maps.put(foods.get(i).getType(), ++k);
                //System.out.println(k+" "+foods.get(i).getName());
                foodLists.add(foodList);
                foodItem.setType(foods.get(i).getType());
                foodItem.setList(foodLists);
                foodItems.add(foodItem);
            }
        }
        return foodItems;
    }
}
