package com.springbootquiz.controller;

import com.springbootquiz.model.Chat;
import com.springbootquiz.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    @MessageMapping("/chat")
    @SendTo("/topic/chat")
    public Chat greeting(User user) throws Exception {
        return new Chat(user.getName() + ":" +user.getMessage());
    }
}
