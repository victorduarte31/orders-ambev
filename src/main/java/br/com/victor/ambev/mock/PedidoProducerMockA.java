package br.com.victor.ambev.mock;

import br.com.victor.ambev.config.RabbitConfig;
import br.com.victor.ambev.rest.dto.ItemPedidoDTO;
import br.com.victor.ambev.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PedidoProducerMockA {

    private final RabbitTemplate rabbitTemplate;

    public void enviarPedido() {

        PedidoDTO pedidoDTO = PedidoDTO.builder()
                .orderId("pedido-12345")
                .itens(List.of(
                        ItemPedidoDTO.builder()
                                .nomeProduto("Notebook Dell Inspiron")
                                .quantidade(1)
                                .valorUnitario(new BigDecimal("3500.00"))
                                .build(),
                        ItemPedidoDTO.builder()
                                .nomeProduto("Mouse Logitech")
                                .quantidade(2)
                                .valorUnitario(new BigDecimal("120.50"))
                                .build()
                )).build();


        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_PEDIDOS,
                RabbitConfig.ROUTING_KEY_PEDIDOS,
                pedidoDTO
        );
    }

}
