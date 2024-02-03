package com.example.demo.service;

import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.dto.response.UserLocationResponseDto;
import com.example.demo.entities.UserLocation;
import java.util.List;
import org.springframework.stereotype.Service;

public interface UserLocationService {
  void saveUserLocation(UserLocationRequestDto userLocationRequestDto);

  UserLocationResponseDto getCurrentLocation(String userId);

  List<UserLocationResponseDto> getHistoricalLocations(String userId, int limit);

  List<UserLocationResponseDto> getAllHistoricalLocations(String userId);

  boolean checkExistsUserId(String userId);
}
