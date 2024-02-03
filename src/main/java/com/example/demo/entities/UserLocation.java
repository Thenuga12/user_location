package com.example.demo.entities;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserLocation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String userId;
  private double latitude;
  private double longitude;
  private Timestamp timeStamp;
}
