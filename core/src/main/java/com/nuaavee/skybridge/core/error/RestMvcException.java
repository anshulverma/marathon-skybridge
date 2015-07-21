package com.nuaavee.skybridge.core.error;

public class RestMvcException extends Exception {

  public RestMvcException() {
    super();
  }

  public RestMvcException(Throwable cause) {
    super(cause);
  }

  public RestMvcException(String message, Throwable cause) {
    super(message, cause);
  }
}
