package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {

    boolean insertMerchant(User user);

    List<Integer> getExistM_id();

    List<String> getExistM_name();

    String getPasswordByName(User user);

    User getUserByName(User user);
}
