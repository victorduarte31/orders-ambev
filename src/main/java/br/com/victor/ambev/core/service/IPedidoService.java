package br.com.victor.ambev.core.service;

import br.com.victor.ambev.core.entity.Pedido;
import br.com.victor.ambev.rest.dto.PedidoDTO;

public interface IPedidoService {

    void processarPedido(PedidoDTO dto);

    Pedido consultarPedidoPorId(String id);

}
