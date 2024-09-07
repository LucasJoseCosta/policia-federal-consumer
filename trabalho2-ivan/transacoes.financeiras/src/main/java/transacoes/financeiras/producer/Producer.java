package transacoes.financeiras.producer;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class Producer {
	
	private static final Logger logger = Logger.getLogger(Producer.class.getName());

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void enviarEventos() {
        LeitorArquivo leitor = new LeitorArquivo();
        List<Transacao> transacoes = leitor.lerArquivo();

        for (Transacao transacao : transacoes) {
            String mensagem = transacao.toString();
            logger.log(Level.INFO, "Enviando: {0}", mensagem);
            rabbitTemplate.convertAndSend("transacoes.financeiras", mensagem);
        }
    }
	
}
