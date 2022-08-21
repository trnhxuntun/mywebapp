package com.mycompany.controller;

import com.mycompany.Model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private UserService userService;

    @MessageMapping("/hello")
    @SendTo("/topic/messages")
    public String send(String username) {
        return username+" vừa đăng nhập hệ thống!!!";
    }
}
