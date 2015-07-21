package com.nuaavee.skybridge.core.error;

public class JsonParsingException extends RestMvcException {

  public JsonParsingException() {
    super();
  }

  public JsonParsingException(Throwable cause) {
    super(cause);
  }

  public JsonParsingException(String message, Throwable cause) {
    super(message, cause);
  }
}
