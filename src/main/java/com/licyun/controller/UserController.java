package com.licyun.controller;

import com.licyun.model.User;
import com.licyun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by 李呈云
 * Description:
 * 2016/10/14.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 访问首页
     * @param model
     * @return
     */
    @RequestMapping(value = {"/"}, method = {RequestMethod.GET})
    public String index(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    /**
     * 添加用户
     * @param model
     * @return
     */
    @RequestMapping(value = {"/add"}, method = {RequestMethod.GET})
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "add";
    }
    @RequestMapping(value = {"/add"}, method = {RequestMethod.POST})
    public String addUser(@Valid User user, Model model){
        userService.insertUser(user);
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @RequestMapping(value = {"/deleteUser-{id}"}, method = {RequestMethod.GET})
    public String deleteUser(@PathVariable int id, Model model){
        userService.deleteById(id);
        model.addAttribute("users", userService.findAllUsers());
        return "index";
    }

    @RequestMapping(value = {"/single"}, method = {RequestMethod.GET})
    public String singleUser(Model model){
        model.addAttribute("user", userService.findByName("licyun"));
        return "single";
    }
}
