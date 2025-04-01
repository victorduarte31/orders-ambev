package br.com.victor.ambev.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    // Filas e Routing Keys
    public static final String QUEUE_PEDIDOS = "produtoA-pedidos";
    public static final String QUEUE_DLQ_PEDIDOS = "produtoA-pedidos-dlq";
    public static final String ROUTING_KEY_PEDIDOS = "pedido.routingkey";
    public static final String ROUTING_KEY_DLQ_PEDIDOS = "pedido.routingkey.dlq";

    // Exchanges
    public static final String EXCHANGE_PEDIDOS = "pedido-exchange";
    public static final String EXCHANGE_DLQ_PEDIDOS = "pedido-exchange-dlq";

    @Bean
    DirectExchange pedidosExchange() {
        return new DirectExchange(EXCHANGE_PEDIDOS);
    }

    @Bean
    DirectExchange pedidosDLQExchange() {
        return new DirectExchange(EXCHANGE_DLQ_PEDIDOS);
    }

    @Bean
    Queue pedidosQueue() {
        return QueueBuilder.durable(QUEUE_PEDIDOS)
                .deadLetterExchange(EXCHANGE_DLQ_PEDIDOS)
                .deadLetterRoutingKey(ROUTING_KEY_DLQ_PEDIDOS)
                .build();
    }

    @Bean
    Queue pedidosDLQQueue() {
        return QueueBuilder.durable(QUEUE_DLQ_PEDIDOS).build();
    }

    @Bean
    Binding pedidoBinding() {
        return BindingBuilder
                .bind(pedidosQueue())
                .to(pedidosExchange())
                .with(ROUTING_KEY_PEDIDOS);
    }

    @Bean
    Binding pedidoDLQBinding() {
        return BindingBuilder
                .bind(pedidosDLQQueue())
                .to(pedidosDLQExchange())
                .with(ROUTING_KEY_DLQ_PEDIDOS);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}