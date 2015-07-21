package com.nuaavee.skybridge.core;

import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import com.nuaavee.skybridge.events.EventMarshaller;
import com.nuaavee.skybridge.events.EventProcessor;
import com.nuaavee.skybridge.events.MarathonEventSubscriber;
import com.nuaavee.skybridge.events.listener.EventListener;

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "com.nuaavee.skybridge.core",
               excludeFilters = {
                 @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = CoreConfiguration.class)
               })
public class TestCoreConfiguration {

  @Bean
  EventProcessor createEventProcessor() {
    return Mockito.mock(EventProcessor.class);
  }

  @Bean
  EventListener createEventListener() {
    return Mockito.mock(EventListener.class);
  }

  @Bean
  MarathonEventSubscriber createEventSubscriber() {
    return Mockito.mock(MarathonEventSubscriber.class);
  }

  @Bean
  EventMarshaller createEventMarshaller() {
    return Mockito.mock(EventMarshaller.class);
  }
}
