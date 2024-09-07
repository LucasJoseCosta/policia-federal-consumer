package policia.federal.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfigPolicia {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("transacoes.suspeitas", true, false);
    }

    @Bean
    public Queue filaPoliciaFederal() {
        return new Queue("policia.federal", true);
    }

    @Bean
    public Binding bindingPolicia(FanoutExchange fanoutExchange, Queue filaPoliciaFederal) {
        return BindingBuilder.bind(filaPoliciaFederal).to(fanoutExchange);
    }
}
