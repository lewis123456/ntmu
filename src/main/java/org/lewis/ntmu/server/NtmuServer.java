package org.lewis.ntmu.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.lewis.ntmu"})
public class NtmuServer {
    public static void main(String[] args) {
        SpringApplication.run(NtmuServer.class, args);
    }
}
