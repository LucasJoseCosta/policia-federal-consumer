package policia.federal.consumer;

import javax.annotation.PostConstruct;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.amqp.core.Queue;

@SpringBootApplication
@ComponentScan({"policia.federal"})
public class TransacoesConsumerApp {

	@Autowired
	private AmqpAdmin amqpAdmin;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	private Queue filaTransacoes;

	public static void main(String[] args) throws Exception {
		SpringApplication.run(policia.federal.consumer.TransacoesConsumerApp.class, args);
	}

	@PostConstruct
	public void criarFila() {
		this.filaTransacoes = new Queue("transacoes.financeiras", true);
		this.amqpAdmin.declareQueue(this.filaTransacoes);
	}
}
