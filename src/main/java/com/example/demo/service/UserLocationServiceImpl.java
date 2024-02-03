package com.example.demo.service;


import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.dto.response.UserLocationResponseDto;
import com.example.demo.entities.UserLocation;
import com.example.demo.repository.UserLocationRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLocationServiceImpl implements UserLocationService {
  @Autowired
  private UserLocationRepository userLocationRepository;

  @Override
  public void saveUserLocation(UserLocationRequestDto userLocationRequestDto) {
    UserLocation userLocation = new UserLocation();
    BeanUtils.copyProperties(userLocationRequestDto, userLocation);
    userLocationRepository.save(userLocation);
  }

  @Override
  public UserLocationResponseDto getCurrentLocation(String userId) {
    Optional<UserLocation> userLocation = userLocationRepository.findByUserId(userId);
    UserLocationResponseDto userLocationResponseDto = new UserLocationResponseDto();
    if (userLocation.isPresent()) {
      BeanUtils.copyProperties(userLocation.get(), userLocationResponseDto);
    }
    return userLocationResponseDto;
  }

  @Override
  public List<UserLocationResponseDto> getHistoricalLocations(String userId, int limit) {
    List<UserLocationResponseDto> userLocationResponseDtoList = new ArrayList<>();
    List<UserLocation> userLocationList = userLocationRepository.findTopNByUserIdOrderByTimeStampDesc(
        userId);
    for (UserLocation userLocation : userLocationList) {
      UserLocationResponseDto userLocationResponseDto = new UserLocationResponseDto();
      BeanUtils.copyProperties(userLocation, userLocationResponseDto);
      userLocationResponseDtoList.add(userLocationResponseDto);
    }
    return userLocationResponseDtoList;
  }

  @Override
  public List<UserLocationResponseDto> getAllHistoricalLocations(String userId) {
    List<UserLocationResponseDto> userLocationResponseDtoList = new ArrayList<>();
    List<UserLocation> userLocationList = userLocationRepository.findAllByUserIdOrderByTimeStampDesc(
        userId);
    for (UserLocation userLocation : userLocationList) {
      UserLocationResponseDto userLocationResponseDto = new UserLocationResponseDto();
      BeanUtils.copyProperties(userLocation, userLocationResponseDto);
      userLocationResponseDtoList.add(userLocationResponseDto);
    }
    return userLocationResponseDtoList;
  }
}
