package br.com.victor.ambev.rest.dto;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ItemPedidoDTO(String nomeProduto,
                            Integer quantidade,
                            BigDecimal valorUnitario) {
}
