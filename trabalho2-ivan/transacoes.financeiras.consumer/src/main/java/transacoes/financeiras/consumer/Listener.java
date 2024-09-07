package transacoes.financeiras.consumer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Listener {

	private static final Logger logger = Logger.getLogger(Listener.class.getName());

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private FanoutExchange exchangeSuspeitas;

	@RabbitListener(queues = "transacoes.financeiras")
	public void listen(String mensagem) {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			logger.log(Level.SEVERE, "Erro ao processar mensagem", e);
		}

		logger.log(Level.INFO, "Transação recebida: {0}", mensagem);

		String[] parts = mensagem.split(",");
		if (parts.length > 3) {
			try {
				String valorString = parts[3].trim();
				if (valorString.startsWith("valor=")) {
					valorString = valorString.substring("valor=".length());
				}
				double valor = Double.parseDouble(valorString);

				if (valor >= 40000) {
					String notificacao = "Notificação: " + mensagem;
					rabbitTemplate.convertAndSend(exchangeSuspeitas.getName(), "", notificacao);
				}
			} catch (NumberFormatException e) {
				logger.log(Level.SEVERE, "Erro ao analisar o valor", e);
			}
		}
	}
}
