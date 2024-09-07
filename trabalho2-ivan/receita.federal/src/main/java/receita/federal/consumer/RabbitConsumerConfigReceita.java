package receita.federal.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfigReceita {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("transacoes.suspeitas", true, false);
    }

    @Bean
    public Queue filaReceitaFederal() {
        return new Queue("receita.federal", true);
    }

    @Bean
    public Binding bindingReceita(FanoutExchange fanoutExchange, Queue filaReceitaFederal) {
        return BindingBuilder.bind(filaReceitaFederal).to(fanoutExchange);
    }

}
