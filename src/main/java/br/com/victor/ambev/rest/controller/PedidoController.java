package br.com.victor.ambev.rest.controller;

import br.com.victor.ambev.core.entity.Pedido;
import br.com.victor.ambev.core.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class PedidoController {

    private final IPedidoService pedidoService;

    @GetMapping("/{orderId}")
    public ResponseEntity<Pedido> consultarPedido(@PathVariable String orderId) {
        Pedido pedido = pedidoService.consultarPedidoPorId(orderId);
        if(pedido != null){
            return ResponseEntity.ok(pedido);
        }
        return ResponseEntity.notFound().build();
    }



}
