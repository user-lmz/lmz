package com.example.ordersystem.mapper;

import com.example.ordersystem.pojo.BlanketOrder;
import com.example.ordersystem.pojo.Order;
import com.example.ordersystem.pojo.OrderItem;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    boolean insertItem(OrderItem orderItem);

    List<Integer> getIdFromItem();

    boolean insertOrder(Order order);

    List<Integer> getO_idFromOrder();

    List<Order> getOrder();

    boolean setOrderMessageAndTime(String message, String time, int o_id);

    List<Integer> getO_idByU_id(int u_id);

    boolean deleteItemByO_id(int o_id);

    boolean deleteOrderByU_id(int u_id);

    List<OrderItem> getOrderItemByO_id(int o_id);

    List<BlanketOrder> getBlanketOrder();

    boolean deleteOrderByO_id(int o_id);
}
