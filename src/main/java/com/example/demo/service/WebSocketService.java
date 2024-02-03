package com.example.demo.service;

import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.dto.response.UserLocationResponseDto;
import org.springframework.messaging.handler.annotation.Payload;

public interface WebSocketService {
  public void sendLocation(@Payload UserLocationRequestDto locationMessage, String userId);
}
