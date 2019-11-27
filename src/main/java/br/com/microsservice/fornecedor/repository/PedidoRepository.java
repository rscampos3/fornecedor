package br.com.microsservice.fornecedor.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.microsservice.fornecedor.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, Long>{

}
