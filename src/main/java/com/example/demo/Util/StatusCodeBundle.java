package com.example.demo.Util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:MessageAndCodes.properties")
@Getter
@Setter
public class StatusCodeBundle {
  // Common Success code
  @Value("${code.success.common}")
  private String commonSuccessCode;

  @Value("${code.failure.common}")
  private String failureCode;

  @Value("${message.success.save.user.location}")
  private String saveUserLocationSuccessMessage;

  @Value("${message.failure.save.user.location}")
  private String unableToSaveUserLocationMessage;

  @Value("${code.validation.user.notExists}")
  private String codeForUnableToSave;

  @Value("${message.success.get.user.location}")
  private String locationGetSuccessMessage;

  @Value("${message.not.found.location}")
  private String locationNotFoundMessage;

  @Value("${code.location.not.found}")
  private String codeLocationNotFound;
}
