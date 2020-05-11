package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.BlanketOrder;
import com.example.ordersystem.pojo.Order;
import com.example.ordersystem.pojo.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert("INSERT INTO `item` (`id`, `f_id`, `num`, `subtotal`, `o_id`) VALUES" +
            "(#{id}, #{f_id}, #{num}, #{subtotal}, #{o_id})")
    boolean insertItem(OrderItem orderItem);

    @Select("select id from item")
    List<Integer> getIdFromItem();

    @Insert("insert into `order`(`o_id`, `u_id`, `o_totalPrice`) values" +
            "(#{o_id}, #{u_id}, #{o_totalPrice})")
    boolean insertOrder(Order order);

    @Select("select o_id from `order`")
    List<Integer> getO_idFromOrder();

    @Select("select message, time, u_name from `order`,user " +
            "where `order`.u_id = user.u_id order by o_id desc")
    List<Order> getOrder();

    @Update("update `order` set message=#{message}, time=#{time} where o_id=#{o_id}")
    boolean setOrderMessageAndTime(String message, String time, int o_id);

    @Select("select o_id from `order` where u_id=#{u_id}")
    List<Integer> getO_idByU_id(int u_id);

    @Delete("delete from item where o_id=#{o_id}")
    boolean deleteItemByO_id(int o_id);

    @Delete("delete from `order` where u_id=#{u_id}")
    boolean deleteOrderByU_id(int u_id);

    @Select("select id, f_name, f_price, num, subtotal from item,food where food.f_id = item.f_id")
    List<OrderItem> getOrderItemByO_id(int o_id);

    @Select("select o_id, message, time, u_name, o_totalPrice "+
            "from user,`order` where user.u_id = `order`.u_id")
    List<BlanketOrder> getBlanketOrder();

    @Delete("delete from `order` where o_id=#{o_id}")
    boolean deleteOrderByO_id(int o_id);
}
