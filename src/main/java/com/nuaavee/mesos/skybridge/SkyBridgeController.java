package com.nuaavee.mesos.skybridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SkyBridgeController {

  @Autowired
  private SampleService sampleService;

  @RequestMapping("/test/{id}")
  @ResponseBody
  public DummyResponse test(@PathVariable Integer id) {
    return new DummyResponse("I got id " + id + " and message: " + sampleService.get());
  }
}
