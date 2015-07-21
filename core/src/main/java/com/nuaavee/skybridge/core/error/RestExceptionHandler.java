package com.nuaavee.skybridge.core.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.nuaavee.skybridge.core.RestResponse;
import com.nuaavee.skybridge.events.UnknownEventTypeException;

@ControllerAdvice
public class RestExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public RestResponse handleGenericError(Exception exception) {
    LOG.error(exception.getMessage(), exception);
    return new RestResponse("generic error: " + exception.getMessage());
  }

  @ExceptionHandler(JsonParsingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RestResponse handleParsingError(JsonParsingException exception) {
    return new RestResponse("unable to parse event json, cause: " + exception.getMessage());
  }

  @ExceptionHandler(UnknownEventTypeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RestResponse handleParsingError(UnknownEventTypeException exception) {
    return new RestResponse("unknown event type: " + exception.getEventType());
  }
}
