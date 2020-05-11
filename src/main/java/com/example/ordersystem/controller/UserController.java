package com.example.ordersystem.controller;

import com.example.ordersystem.pojo.User;
import com.example.ordersystem.service.FoodService;
import com.example.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;

    User t_user;

    @RequestMapping("")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("userRegister")
    @ResponseBody
    public int toRegister(@RequestBody User user){
        if (!userService.getExistU_name().contains(user.getName())) {
            userService.registerUser(user);
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("checkUserLogin")
    @ResponseBody
    public int checkUserLogin(@RequestBody User user){
        if (userService.getUserByName(user) == null) {
            return -1;
        } else if (userService.checkLogin(user)) {
            t_user = user;
            return 1;
        } else
            return 0;
    }

    @RequestMapping("toIndex")
    public String toIndex(Model model, HttpSession httpSession) {
        if (t_user != null) {
            User user_temp = userService.getUserByName(t_user);
            httpSession.setAttribute("user",user_temp);
            model.addAttribute("items", foodService.getFoodModel());
            User user1 =(User) httpSession.getAttribute("user");
            model.addAttribute("user", user1);
            return "index";
        }else {
            User user1 =(User) httpSession.getAttribute("user");
            model.addAttribute("items", foodService.getFoodModel());
            model.addAttribute("user", user1);
            return "index";
        }

    }

    @RequestMapping("toLogout")
    public String toLogout(Model model, HttpSession httpSession){
        httpSession.removeAttribute("user");
        model.addAttribute("items", foodService.getFoodModel());
        User user1 =(User) httpSession.getAttribute("user");
        model.addAttribute("user", user1);
        return "index";
    }
}
