package com.nttdata.challenge.product_service;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.nttdata.challenge.product_service.model.Product;
import com.nttdata.challenge.product_service.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient; // << Importe esta linha!



@SpringBootApplication
@EnableDiscoveryClient // << Adicione ESTA LINHA
public class ProductServiceApplication {
    // Método principal para iniciar a aplicação Spring Boot
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    // Método Opcional para inicializar dados no banco de dados
    @Bean
public CommandLineRunner initData(ProductRepository repository) {
    return args -> {
        repository.save(new Product(null, "Laptop Dell", "Notebook i7 16GB RAM", 1200.00));
        repository.save(new Product(null, "Mouse Logitech", "Mouse sem fio ergonômico", 25.50));
        repository.save(new Product(null, "Teclado Mecânico", "Teclado RGB switch blue", 80.00));
        System.out.println("Produtos iniciais cadastrados!");
    };
}
}
