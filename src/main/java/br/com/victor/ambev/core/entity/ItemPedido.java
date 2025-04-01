package br.com.victor.ambev.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedido {

    private String nomeProduto;

    private Integer quantidade;

    private BigDecimal valorUnitario;
}