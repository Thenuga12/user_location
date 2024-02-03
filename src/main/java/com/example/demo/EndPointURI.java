package com.example.demo;

public class EndPointURI {
  private static final String BASE_API_PATH = "/users";
  private static final String SLASH = "/";
  private static final String ID = "/{id}";

  public static final String USER_LOCATION =
      BASE_API_PATH + SLASH + "{user_id}" + SLASH + "locations";

  public static final String USER_LOCATION_BY_USER_ID = BASE_API_PATH + SLASH + "{user_id}";
  public static final String HISTORICAL_LOCATION_BY_USER_ID = USER_LOCATION;
}
