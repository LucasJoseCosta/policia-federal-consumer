package policia.federal.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Listener {

	private static final Logger logger = Logger.getLogger(Listener.class.getName());

    @RabbitListener(queues = "policia.federal")
    public void listen(String in) {
        logger.log(Level.WARNING, "Policia Federal - Aviso: Transação recebida: {0}", in);
    }
}
