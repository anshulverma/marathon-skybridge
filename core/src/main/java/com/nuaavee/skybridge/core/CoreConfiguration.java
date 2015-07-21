package com.nuaavee.skybridge.core;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = {"com.nuaavee.skybridge.core", "com.nuaavee.skybridge.events"})
public class CoreConfiguration { }
