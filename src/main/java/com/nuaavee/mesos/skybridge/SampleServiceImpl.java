package com.nuaavee.mesos.skybridge;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

  @Override
  public String get() {
    return "this is a test";
  }
}
