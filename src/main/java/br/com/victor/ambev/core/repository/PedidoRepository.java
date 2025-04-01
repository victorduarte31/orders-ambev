package br.com.victor.ambev.core.repository;

import br.com.victor.ambev.core.entity.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepository extends MongoRepository<Pedido, String> {

    boolean existsByOrderId(String orderId);

    Pedido findByOrderId(String orderId);
}
