package com.nuaavee.skybridge.core.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.nuaavee.skybridge.events.UnknownEventTypeException;

@ControllerAdvice
public class RestExceptionHandler {

  private static final Logger LOG = LoggerFactory.getLogger(RestExceptionHandler.class);

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public RestErrorContent handleGenericError(Exception exception) {
    LOG.error(exception.getMessage(), exception);
    return new RestErrorContent("generic error: " + exception.getMessage());
  }

  @ExceptionHandler(JsonParsingException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RestErrorContent handleParsingError(JsonParsingException exception) {
    return new RestErrorContent("unable to parse event json, cause: " + exception.getMessage());
  }

  @ExceptionHandler(UnknownEventTypeException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public RestErrorContent handleParsingError(UnknownEventTypeException exception) {
    return new RestErrorContent("unknown event type: " + exception.getEventType());
  }
}
