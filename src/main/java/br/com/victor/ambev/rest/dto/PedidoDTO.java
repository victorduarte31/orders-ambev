package br.com.victor.ambev.rest.dto;

import lombok.Builder;

import java.util.List;


@Builder
public record PedidoDTO(String orderId,
                        List<ItemPedidoDTO> itens) {
}
