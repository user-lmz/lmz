package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    void insertUser(User user);
    List<Integer> getExistU_id();
    List<String> getExistU_name();
    String getPasswordByName(User user);
    User getUserByName(User user);
    List<User> getAllUser();
    boolean updateUserById(User user);
    boolean deleteUserById(User user);
}
