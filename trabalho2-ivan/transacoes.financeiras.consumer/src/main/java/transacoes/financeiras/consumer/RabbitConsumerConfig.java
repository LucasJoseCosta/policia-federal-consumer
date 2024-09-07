package transacoes.financeiras.consumer;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConsumerConfig {

    @Bean
    public FanoutExchange exchangeSuspeitas() {
        return new FanoutExchange("transacoes.suspeitas", true, false);
    }

    @Bean
    public Queue filaTransacoesFinanceiras() {
        return new Queue("transacoes.financeiras", true);
    }

    @Bean
    public Queue filaPoliciaFederal() {
        return new Queue("policia.federal", true);
    }

    @Bean
    public Queue filaReceitaFederal() {
        return new Queue("receita.federal", true);
    }

    @Bean
    public Binding bindingFilaPoliciaFederal(FanoutExchange exchangeSuspeitas, Queue filaPoliciaFederal) {
        return BindingBuilder.bind(filaPoliciaFederal).to(exchangeSuspeitas);
    }

    @Bean
    public Binding bindingFilaReceitaFederal(FanoutExchange exchangeSuspeitas, Queue filaReceitaFederal) {
        return BindingBuilder.bind(filaReceitaFederal).to(exchangeSuspeitas);
    }
}
