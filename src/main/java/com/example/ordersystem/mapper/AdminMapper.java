package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Insert("insert into merchant(m_id, m_name, m_password, m_address, m_tel) " +
            "values(#{id}, #{name}, #{password}, #{address}, #{tel})")
    boolean insertUser(User user);

    @Select("select m_id from merchant")
    List<Integer> getExistM_id();

    @Select("select m_name from merchant")
    List<String> getExistM_name();

    @Select("select m_password from merchant where m_name=#{name}")
    String getPasswordByName(User user);

    @Select("select * from merchant where m_name=#{name}")
    @Results({
            @Result(column = "m_id", property = "id"),
            @Result(column = "m_name", property = "name"),
            @Result(column = "m_password", property = "password"),
            @Result(column = "m_address", property = "address"),
            @Result(column = "m_tel", property = "tel")
    })
    User getUserByName(User user);
}
