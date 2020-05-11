package com.example.ordersystem.controller;

import com.example.ordersystem.pojo.Order;
import com.example.ordersystem.pojo.OrderItem;
import com.example.ordersystem.pojo.Select;
import com.example.ordersystem.pojo.User;
import com.example.ordersystem.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OrderController {
    List<Select> selects_a;
    double totalPrice;
    static int i,j;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "confirmSelects")
    @ResponseBody
    public String confirmSelects(@RequestBody List<Select> selects) {
        selects_a = selects;
        totalPrice = 0.0f;
        for (Select select: selects){
            select.setMoney(select.getPrice()*select.getCount());
            totalPrice += select.getMoney();
            System.out.println(select.getF_id()+" "+select.getName()+" "+select.getCount()+" ￥"+select.getPrice()+" "+select.getMoney());
        }
        return "/toOrder";
    }
    @RequestMapping("toOrder")
    public String toOrder(HttpSession httpSession, Model model) {
        User user = (User)httpSession.getAttribute("user");
        System.out.println(totalPrice);
        model.addAttribute("user",user);
        model.addAttribute("selects", selects_a);
        model.addAttribute("totalPrice", totalPrice);
        return "orderResult";
    }
    @RequestMapping("confirmOrder")
    public String confirmOrder(HttpSession httpSession) {
        i=1;
        j=1;
        User user = (User)httpSession.getAttribute("user");
        //List<OrderItem> orderItems = new ArrayList<>();
        List<Integer> ids = orderService.getIdFromItem();
        List<Integer> o_ids = orderService.getO_idFromOrder();
        OrderItem orderItem = new OrderItem();
        while (ids.contains(i)){
            i++;
        }
        while (o_ids.contains(j)){
            j++;
        }
        Order order = new Order();
        order.setO_id(j);
        order.setU_id(user.getId());
        order.setO_totalPrice(totalPrice);
        orderService.insertOrder(order);
        for (Select select: selects_a) {
            orderItem.setId(i++);
            orderItem.setF_id(select.getF_id());
            orderItem.setNum(select.getCount());
            orderItem.setSubtotal(select.getMoney());
            orderItem.setO_id(j);
            System.out.println(orderItem.getId()+" "+orderItem.getF_id()+" "+orderItem.getNum()+" "+orderItem.getSubtotal());

            orderService.insertItem(orderItem);
        }
        return "successPay";
    }

    @RequestMapping("cancelOrder")
    public String cancelOrder(){
        return "redirect:/toIndex";
    }

    @RequestMapping("publishComment")
    @ResponseBody
    public String publishComment(@RequestBody Order order){
        orderService.setOrderMessageAndTime(order.getMessage(), order.getTime(), j);
        return "/showComments";
    }

    @RequestMapping("showComments")
    public String showComments(Model model){
        List<Order> orders = orderService.getOrder();
        model.addAttribute("orders", orders);
        return "successPay";
    }

    @RequestMapping("backToIndex")
    public String backToIndex(){
        return "redirect:/toIndex";
    }

}
