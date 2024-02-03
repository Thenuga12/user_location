package com.example.demo.dto.request;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLocationRequestDto {
  private Long id;
  private String userId;
  private double latitude;
  private double longitude;
  private Timestamp timeStamp;
}
