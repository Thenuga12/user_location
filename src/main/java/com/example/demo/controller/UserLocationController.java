package com.example.demo.controller;

import com.example.demo.EndPointURI;
import com.example.demo.Util.Constants;
import com.example.demo.Util.StatusCodeBundle;
import com.example.demo.commonResponse.BaseResponse;
import com.example.demo.commonResponse.ContentResponse;
import com.example.demo.dto.request.UserLocationRequestDto;
import com.example.demo.dto.response.UserLocationResponseDto;
import com.example.demo.restEnum.RequestStatus;
import com.example.demo.service.UserLocationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLocationController {
  @Autowired
  private UserLocationService userLocationService;
  @Autowired
  private StatusCodeBundle statusCodeBundle;

  @PostMapping(value = EndPointURI.USER_LOCATION)
  public ResponseEntity<Object> postUserLocation(@PathVariable("user_id") String userId,
      @RequestBody UserLocationRequestDto userLocationRequestDto) {
    try {
      userLocationRequestDto.setUserId(userId);
      userLocationService.saveUserLocation(userLocationRequestDto);
      return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
          statusCodeBundle.getCommonSuccessCode(),
          statusCodeBundle.getSaveUserLocationSuccessMessage()));

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(new BaseResponse(RequestStatus.FAILURE.getStatus(),
              statusCodeBundle.getCodeForUnableToSave(),
              statusCodeBundle.getUnableToSaveUserLocationMessage()));

    }
  }

  @GetMapping(value = EndPointURI.USER_LOCATION_BY_USER_ID)
  public ResponseEntity<Object> getCurrentLocation(@PathVariable("user_id") String userId) {

    UserLocationResponseDto userLocation = userLocationService.getCurrentLocation(userId);

    if (userLocation != null) {
      return ResponseEntity.ok((new ContentResponse<>(Constants.LOCATION, userLocation,
          RequestStatus.SUCCESS.getStatus(), statusCodeBundle.getCommonSuccessCode(),
          statusCodeBundle.getLocationGetSuccessMessage())));
    } else {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          statusCodeBundle.getCodeLocationNotFound(),
          statusCodeBundle.getLocationNotFoundMessage()));
    }
  }

  @GetMapping(value = EndPointURI.HISTORICAL_LOCATION_BY_USER_ID)
  public ResponseEntity<Object> getHistoricalLocations(@PathVariable("user_id") String userId,
      @RequestParam(required = false) int limit) {
    List<UserLocationResponseDto> historicalLocations;
    if (limit != 0) {
      historicalLocations = userLocationService.getHistoricalLocations(userId, limit);
    } else {
      historicalLocations = userLocationService.getAllHistoricalLocations(userId);
    }
    if (historicalLocations.isEmpty()) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          statusCodeBundle.getCodeLocationNotFound(),
          statusCodeBundle.getLocationNotFoundMessage()));
    } else {
      return ResponseEntity.ok(historicalLocations);
    }
  }
}