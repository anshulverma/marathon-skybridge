package com.nuaavee.skybridge.core.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestErrorContent {

  @JsonProperty
  private String message;

}
