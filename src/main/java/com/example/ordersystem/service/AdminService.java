package com.example.ordersystem.service;

import com.example.ordersystem.mapper.AdminMapper;
import com.example.ordersystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    static int i=1;

    public void registerUser(User user){
        List<Integer> ids = adminMapper.getExistM_id();
        while (ids.contains(i))
            i++;
        user.setId(i++);
        adminMapper.insertUser(user);
    }

    public boolean checkLogin(User user){
        String password = adminMapper.getPasswordByName(user);
        return password != null && password.equals(user.getPassword());
    }

    public List<String> getExistM_name(){
        return adminMapper.getExistM_name();
    }

    public User getUserByName(User user){
        return adminMapper.getUserByName(user);
    }
}
