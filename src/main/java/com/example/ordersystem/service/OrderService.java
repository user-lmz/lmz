package com.example.ordersystem.service;

import com.example.ordersystem.mapper.OrderMapper;
import com.example.ordersystem.pojo.BlanketOrder;
import com.example.ordersystem.pojo.Order;
import com.example.ordersystem.pojo.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OrderService {
    @Resource
    private OrderMapper orderMapper;

    public boolean insertItem(OrderItem orderItem) {
        return orderMapper.insertItem(orderItem);
    }

    public List<Integer> getIdFromItem() {
        return orderMapper.getIdFromItem();
    }

    public boolean insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    public List<Integer> getO_idFromOrder() {
        return orderMapper.getO_idFromOrder();
    }

    public List<Order> getOrder() {
        return orderMapper.getOrder();
    }

    public boolean setOrderMessageAndTime(String message, String time, int o_id){
        return orderMapper.setOrderMessageAndTime(message, time, o_id);
    }

    public List<Integer> getO_idByU_id(int u_id){
        return orderMapper.getO_idByU_id(u_id);
    }

    public boolean deleteItemByO_id(int o_id){
        return orderMapper.deleteItemByO_id(o_id);
    }

    public boolean deleteOrderByU_id(int u_id){
        return orderMapper.deleteOrderByU_id(u_id);
    }

    public List<OrderItem> getOrderItemByO_id(int o_id){
        return orderMapper.getOrderItemByO_id(o_id);
    }

    public List<BlanketOrder> getBlanketOrder(){
        return orderMapper.getBlanketOrder();
    }

    public boolean deleteOrderByO_id(int o_id) {
        return orderMapper.deleteOrderByO_id(o_id);
    }
}
