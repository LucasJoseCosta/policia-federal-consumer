package transacoes.financeiras.producer;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	@Bean
	public Queue filaTransacoesFinanceiras() {
		return new Queue("transacoes.financeiras", true);
	}
}
