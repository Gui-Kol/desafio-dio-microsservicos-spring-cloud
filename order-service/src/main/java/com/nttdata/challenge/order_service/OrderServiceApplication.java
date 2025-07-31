package com.nttdata.challenge.order_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // << Importe esta linha!
import org.springframework.cloud.openfeign.EnableFeignClients; // << Importe esta linha!

@SpringBootApplication
@EnableDiscoveryClient // << Adicione ESTA LINHA
@EnableFeignClients // << Adicione ESTA LINHA
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}