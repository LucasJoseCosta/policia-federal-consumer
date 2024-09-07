package policia.federal.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"policia.federal"})
public class ExchangeConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ExchangeConsumerApp.class, args);
    }
}