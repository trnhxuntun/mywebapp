package com.mycompany.controller;

import com.mycompany.Model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private UserService userService;

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String send(@Payload String username) {
        System.out.println("da gui thong bao");
        return username+" vừa đăng nhập hệ thống!!!";
    }
    @MessageMapping("/update")
    @SendTo("/topic/update")
    public String update() {
        System.out.println("thong bao da gui");
        return "vừa có dữ liệu được thêm vào hệ thống!!!";
    }
}
