package com.example.demo.controller;

import com.example.demo.EndPointURI;
import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

  @Autowired
  private SimpMessagingTemplate messagingTemplate;
  @Autowired
  private WebSocketService webSocketService;

  @MessageMapping(value = EndPointURI.USER_LOCATION_BY_USER_ID)
  public void sendLocation(@Payload UserLocationRequestDto locationMessage, String userId) {
    webSocketService.sendLocation(locationMessage, userId);
  }
}

