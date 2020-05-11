package com.example.ordersystem.controller;

import com.example.ordersystem.pojo.BlanketOrder;
import com.example.ordersystem.pojo.Food;
import com.example.ordersystem.pojo.OrderItem;
import com.example.ordersystem.pojo.User;
import com.example.ordersystem.service.AdminService;
import com.example.ordersystem.service.FoodService;
import com.example.ordersystem.service.OrderService;
import com.example.ordersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    FoodService foodService;
    @Autowired
    AdminService adminService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    static int i;
    User t_user;

    @RequestMapping("adminRegister")
    @ResponseBody
    public int toRegister(@RequestBody User user){
        if (!adminService.getExistM_name().contains(user.getName())) {
            adminService.registerUser(user);
            return 1;
        }else {
            return 0;
        }
    }

    @RequestMapping("checkAdminLogin")
    @ResponseBody
    public int checkUserLogin(@RequestBody User user){
        if (adminService.checkLogin(user)){
            t_user = user;
            return 1;
        }
        else
            return 0;
    }

    @RequestMapping("toAdmin")
    public String toAdmin(Model model, HttpSession httpSession) {
        List<BlanketOrder> blanketOrders = orderService.getBlanketOrder();
        for (BlanketOrder blanketOrder: blanketOrders){
            List<OrderItem> orderItems = orderService.getOrderItemByO_id(blanketOrder.getO_id());
            blanketOrder.setOrderItems(orderItems);
        }
        if (t_user != null) {
            User admin_temp = adminService.getUserByName(t_user);
            httpSession.setAttribute("admin",admin_temp);
            model.addAttribute("foods", foodService.selectFood());
            model.addAttribute("users",userService.getAllUser());
            model.addAttribute("orders",blanketOrders);
            model.addAttribute("user",t_user);
            return "admin";
        }else {
            User user1 =(User) httpSession.getAttribute("admin");
            model.addAttribute("foods", foodService.selectFood());
            model.addAttribute("users",userService.getAllUser());
            model.addAttribute("orders",blanketOrders);
            model.addAttribute("user",user1);
            return "admin";
        }
    }

    @RequestMapping("adminLogout")
    public String toLogout(Model model, HttpSession httpSession){
        httpSession.removeAttribute("admin");
        model.addAttribute("foods", foodService.selectFood());
        User user1 =(User) httpSession.getAttribute("admin");
        model.addAttribute("user", user1);
        return "admin";
    }

    @RequestMapping("back")
    public String toBack(){
        return "redirect:/toAdmin";
    }

    @RequestMapping("updateFood")
    @ResponseBody
    public String updateFood(@RequestBody Food food){
        System.out.println(food.getName()+" "+food.getMaterial()+" "+food.getType()+" "+food.getPrice());
        foodService.updateFoodById(food);
        return "/toAdmin";
    }

    @RequestMapping("deleteFood")
    @ResponseBody
    public String deleteFood(@RequestBody Food food) throws IOException{
        //String realPath = "src\\main\\resources\\static\\img\\";
        String realPath = ResourceUtils.getURL("classpath:static/img/").getPath()
                .replace("/","\\");
        File file = new File(realPath+food.getType()+"\\"+food.getImg());
        if (file.exists()){
            if (file.delete()){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("文件不存在");
        }
        foodService.deleteFoodById(food);
        return "/toAdmin";
    }

    @RequestMapping("updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user){
        userService.updateUserById(user);
        return "/toAdmin";
    }

    @RequestMapping("deleteUser")
    @ResponseBody
    public String deleteUser(@RequestBody User user){
        List<Integer> o_ids = orderService.getO_idByU_id(user.getId());
        for (int id: o_ids) {
            orderService.deleteItemByO_id(id);
        }
        orderService.deleteOrderByU_id(user.getId());
        userService.deleteUserById(user);
        return "/toAdmin";
    }

    @RequestMapping("deleteOrder")
    @ResponseBody
    public String deleteOrder(@RequestBody BlanketOrder blanketOrder){
        int o_id = blanketOrder.getO_id();
        orderService.deleteItemByO_id(o_id);
        orderService.deleteOrderByO_id(o_id);
        return "/toAdmin";
    }

    //用于接收文件
    @RequestMapping(value = "/uploads",method = RequestMethod.POST)
    public ModelAndView upload(MultipartFile photo, Food food) throws IOException {
        i=1;
        List<Integer> ids = foodService.getF_ids();
        while (ids.contains(i)){
            i++;
        }
        food.setId(i);
        //获取文件的名称
        final String fileName = photo.getOriginalFilename();
        food.setImg(fileName);
        ModelAndView mv = new ModelAndView();
        foodService.insertFood(food);
        //判断用户是否上传了文件
        if(!photo.isEmpty()){

            //文件上传的地址
            //String realPath = "src\\main\\resources\\static\\img\\";
            String realPath = ResourceUtils.getURL("classpath:static/img").getPath()
                    .replace("/","\\");

            File path1 = new File(realPath);
            //用于查看路径是否正确
            if (!path1.exists()) path1 = new File("");
            System.out.println(path1);

            //限制文件上传的类型
            String contentType = photo.getContentType();
            if("image/jpeg".equals(contentType) || "image/jpg".equals(contentType) ){
                File file1 = new File(path1.getAbsolutePath());
                if(!file1.exists()) file1.mkdirs();
                String path = file1.getAbsolutePath()+"/"+food.getType()+"/";
                //完成文件的上传
                File file = new File(path + fileName);
                photo.transferTo(file);
                System.out.println("图片上传成功!");
                System.out.println(file.getPath());
                mv.setViewName("redirect:/toAdmin");
                return mv;
            } else {
                System.out.println("上传失败！");
                mv.setViewName("admin");
                return mv;
            }
        } else {
            System.out.println("上传失败！");
            mv.setViewName("admin");
            return mv;
        }
    }
}
