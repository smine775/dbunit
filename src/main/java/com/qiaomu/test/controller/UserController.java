package com.qiaomu.test.controller;

import com.qiaomu.test.domain.User;
import com.qiaomu.test.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * ClassName:UserController
 * Package:com.qiaomu.test.controller
 * Decription:
 *
 * @date:2020/7/13 22:36
 * @author:qiaomu
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/1")
        public void test(){
        try {
            String id = "1";
            String name = "qiaomu";
            int age = 20;
            User user  = new User();
            user.setId(id);
            user.setName(name);
            user.setAge(age);
            userService.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
