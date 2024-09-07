package receita.federal.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class Listener {

    private static final Logger logger = Logger.getLogger(Listener.class.getName());

    @RabbitListener(queues = {"transacoes.financeiras"})
    public void listen(String in) {
        processarTransacao(in);
    }

    public void processarTransacao(String in) {
        try {
            Transacao transacao = parseTransacao(in);

            if (transacao.getValor() > 40000) {
                logger.log(Level.WARNING, "ALERTA: Transação de valor elevado: {0}", transacao);
            }

            Thread.sleep(1000L);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Erro ao processar a transação", ex);
        }
    }

    private Transacao parseTransacao(String in) {
        String dados = in.substring(in.indexOf("[") + 1, in.indexOf("]"));
        String[] campos = dados.split(", ");

        String codigo = campos[0].split("=")[1];
        String cedente = campos[1].split("=")[1];
        String pagador = campos[2].split("=")[1];
        Double valor = Double.parseDouble(campos[3].split("=")[1]);
        String vencimento = campos[4].split("=")[1];

        return new Transacao(codigo, cedente, pagador, valor, vencimento);
    }
}
