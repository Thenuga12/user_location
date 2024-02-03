package com.example.demo.service;

import com.example.demo.EndPointURI;
import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.dto.response.UserLocationResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketServiceImpl implements WebSocketService{
  @Autowired
  private SimpMessagingTemplate messagingTemplate;

 @Override
  public void sendLocation(@Payload UserLocationRequestDto locationMessage,String userId) {
   System.out.println("*******"+locationMessage);
    messagingTemplate.convertAndSendToUser(userId,"/topic/location", locationMessage);
  }
}
