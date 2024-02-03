package com.example.demo.controller;

import com.example.demo.EndPointURI;
import com.example.demo.dto.response.UserLocationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;

  @MessageMapping(value = EndPointURI.USER_LOCATION_BY_USER_ID)
  public void sendLocation(@Payload UserLocationResponseDto locationMessage) {
    messagingTemplate.convertAndSend("/topic/location", locationMessage);
  }
}

