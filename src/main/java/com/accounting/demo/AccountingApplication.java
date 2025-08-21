package com.accounting.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackages = {"com.accounting.demo", "com.accounting.demo.api",
                "com.accounting.demo.model", "org.openapitools.configuration"})
public class AccountingApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountingApplication.class, args);
    }
}
