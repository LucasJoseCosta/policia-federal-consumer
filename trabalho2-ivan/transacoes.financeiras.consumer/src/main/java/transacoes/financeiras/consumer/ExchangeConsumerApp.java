package transacoes.financeiras.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"transacoes.financeiras.consumer"})
public class ExchangeConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeConsumerApp.class, args);
    }
}