package com.example.ordersystem.service;

import com.example.ordersystem.mapper.UserMapper;
import com.example.ordersystem.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    static int i=1;

    public void registerUser(User user){
        List<Integer> ids = userMapper.getExistU_id();
        while (ids.contains(i))
            i++;
        user.setId(i++);
        userMapper.insertUser(user);
    }

    public boolean checkLogin(User user){
        String password = userMapper.getPasswordByName(user);
        return password != null && password.equals(user.getPassword());
    }

    public List<String> getExistU_name(){
        return userMapper.getExistU_name();
    }

    public User getUserByName(User user){
        return userMapper.getUserByName(user);
    }

    public boolean updateUserById(User user){
        return userMapper.updateUserById(user);
    }

    public boolean deleteUserById(User user) {
        return userMapper.deleteUserById(user);
    }

    public List<User> getAllUser() {
        return userMapper.getAllUser();
    }

}
