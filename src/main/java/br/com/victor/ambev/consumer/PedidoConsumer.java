package br.com.victor.ambev.consumer;

import br.com.victor.ambev.config.RabbitConfig;
import br.com.victor.ambev.core.service.IPedidoService;
import br.com.victor.ambev.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PedidoConsumer {

    private final IPedidoService pedidoService;

    @RabbitListener(queues = RabbitConfig.QUEUE_PEDIDOS)
    public void consumirPedidoRecebido(PedidoDTO dto) {
        try {
            pedidoService.processarPedido(dto);
        } catch (Exception e) {
            log.error("Erro ao processar pedido: {}. Enviando para DLQ.", dto, e);
            throw new AmqpRejectAndDontRequeueException(e); // Não reenvia, vai diretamente para DLQ
        }
    }
}