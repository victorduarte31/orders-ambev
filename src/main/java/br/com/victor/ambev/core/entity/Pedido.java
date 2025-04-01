package br.com.victor.ambev.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "orders")
public class Pedido {

    @Id
    private String id;

    private String orderId;

    private StatusPedido status;

    private LocalDateTime criadoEm;

    private BigDecimal valorTotal;

    private List<ItemPedido> itens;
}