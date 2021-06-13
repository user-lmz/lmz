package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Insert("insert into merchant(m_id, m_name, m_password, m_address, m_tel) " +
            "values(#{id}, #{name}, #{password}, #{address}, #{tel})")
    boolean insertUser(User user);

    List<Integer> getExistM_id();

    List<String> getExistM_name();

    String getPasswordByName(User user);

    User getUserByName(User user);
}
