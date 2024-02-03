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
import com.example.demo.service.WebSocketService;
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
  @Autowired
  private WebSocketService webSocketService;


  @PostMapping(value = EndPointURI.USER_LOCATION)
  public ResponseEntity<Object> postUserLocation(@PathVariable("user_id") String userId,
      @RequestBody UserLocationRequestDto userLocationRequestDto) {
    userLocationRequestDto.setUserId(userId);
    userLocationService.saveUserLocation(userLocationRequestDto);
    try {
      webSocketService.sendLocation(userLocationRequestDto, userId);
    } catch (Exception e) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          statusCodeBundle.getCodeLocationNotFound(),
          e.getMessage()));
    }
    return ResponseEntity.ok(new BaseResponse(RequestStatus.SUCCESS.getStatus(),
        statusCodeBundle.getCommonSuccessCode(),
        statusCodeBundle.getSaveUserLocationSuccessMessage()));
  }

  @GetMapping(value = EndPointURI.USER_LOCATION_BY_USER_ID)
  public ResponseEntity<Object> getCurrentLocation(@PathVariable("user_id") String userId) {

    if (!userLocationService.checkExistsUserId(userId)) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          statusCodeBundle.getCodeUserIdNotExists(),
          statusCodeBundle.getUserIdNotExistsMessage()));
    }
    return ResponseEntity.ok(
        (new ContentResponse<>(Constants.LOCATION, userLocationService.getCurrentLocation(
            userId),
            RequestStatus.SUCCESS.getStatus(), statusCodeBundle.getCommonSuccessCode(),
            statusCodeBundle.getLocationGetSuccessMessage())));
  }

  @GetMapping(value = EndPointURI.HISTORICAL_LOCATION_BY_USER_ID)
  public ResponseEntity<Object> getHistoricalLocations(@PathVariable("user_id") String userId,
      @RequestParam(required = false) int limit) {
    List<UserLocationResponseDto> historicalLocations;
    historicalLocations = userLocationService.getHistoricalLocations(userId, limit);
    if (historicalLocations.isEmpty()) {
      return ResponseEntity.ok(new BaseResponse(RequestStatus.FAILURE.getStatus(),
          statusCodeBundle.getCodeLocationNotFound(),
          statusCodeBundle.getLocationNotFoundMessage()));
    } else {
      return ResponseEntity.ok(historicalLocations);
    }
  }
}