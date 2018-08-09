package com.my.client;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EntityScan("com/my/client")
public class ClientConfig {

}
