package br.com.victor.ambev.core.service.impl;

import br.com.victor.ambev.core.entity.ItemPedido;
import br.com.victor.ambev.core.entity.Pedido;
import br.com.victor.ambev.core.entity.StatusPedido;
import br.com.victor.ambev.core.repository.PedidoRepository;
import br.com.victor.ambev.core.service.IPedidoService;
import br.com.victor.ambev.rest.dto.ItemPedidoDTO;
import br.com.victor.ambev.rest.dto.PedidoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoService implements IPedidoService {

    private final PedidoRepository repository;
    private final RedisTemplate<String, Object> redisTemplate;

    @Override
    public void processarPedido(PedidoDTO dto) {
        if(repository.existsByOrderId(dto.orderId())) {
            return; // Pedido duplicado
        }

        Pedido pedido = new Pedido();
        pedido.setOrderId(dto.orderId());
        pedido.setStatus(StatusPedido.EM_PROCESSAMENTO);
        pedido.setCriadoEm(LocalDateTime.now());

        BigDecimal valorTotal = BigDecimal.ZERO;
        List<ItemPedido> itens = new ArrayList<>();
        for(ItemPedidoDTO item : dto.itens()) {
            ItemPedido pedidoItem = new ItemPedido();
            pedidoItem.setNomeProduto(item.nomeProduto());
            pedidoItem.setQuantidade(item.quantidade());
            pedidoItem.setValorUnitario(item.valorUnitario());

            valorTotal = valorTotal.add(item.valorUnitario()
                    .multiply(BigDecimal.valueOf(item.quantidade())));
            itens.add(pedidoItem);
        }
        pedido.setValorTotal(valorTotal);
        pedido.setItens(itens);
        pedido.setStatus(StatusPedido.CALCULADO);

        repository.save(pedido);

        // armazenar em cache Redis para consultas futuras rápidas
        redisTemplate.opsForValue().set("pedido:" + pedido.getOrderId(), pedido);

    }


    // consulta para o produto externo B
    @Override
    public Pedido consultarPedidoPorId(String orderId) {
        Pedido pedido = (Pedido) redisTemplate.opsForValue().get("pedido:" + orderId);
        if(pedido == null) {
            pedido = repository.findByOrderId(orderId);
            if(pedido != null){
                redisTemplate.opsForValue().set("pedido:" + orderId, pedido);
            }
        }
        return pedido;
    }

}
