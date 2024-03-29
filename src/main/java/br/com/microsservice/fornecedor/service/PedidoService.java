package br.com.microsservice.fornecedor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microsservice.fornecedor.dto.ItemDoPedidoDTO;
import br.com.microsservice.fornecedor.model.Pedido;
import br.com.microsservice.fornecedor.model.PedidoItem;
import br.com.microsservice.fornecedor.model.Produto;
import br.com.microsservice.fornecedor.repository.PedidoRepository;
import br.com.microsservice.fornecedor.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PedidoService {
	
	private Logger logger = LoggerFactory.getLogger(PedidoService.class);
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;

	public Pedido realizaPedido(List<ItemDoPedidoDTO> itens) {
		
		if(itens == null) {
			return null;
		}
		logger.info("Realizando pedido de {} itens", itens.size() );
		List<PedidoItem> pedidoItens = toPedidoItem(itens);
		Pedido pedido = new Pedido(pedidoItens);
		pedido.setTempoDePreparo(itens.size());
		logger.info("Salvando o pedido número {}", pedido.getId());
		return pedidoRepository.save(pedido);
	}
	
	public Pedido getPedidoPorId(Long id) {
		logger.info("Buscando pedido {}", id );
		return this.pedidoRepository.findById(id).orElse(new Pedido());
	}

	private List<PedidoItem> toPedidoItem(List<ItemDoPedidoDTO> itens) {
		
		List<Long> idsProdutos = itens
				.stream()
				.map(item -> item.getId())
				.collect(Collectors.toList());
		
		List<Produto> produtosDoPedido = produtoRepository.findByIdIn(idsProdutos);
		
		List<PedidoItem> pedidoItens = itens
			.stream()
			.map(item -> {
				Produto produto = produtosDoPedido
						.stream()
						.filter(p -> p.getId() == item.getId())
						.findFirst().get();
				
				PedidoItem pedidoItem = new PedidoItem();
				pedidoItem.setProduto(produto);
				pedidoItem.setQuantidade(item.getQuantidade());
				return pedidoItem;
			})
			.collect(Collectors.toList());
		return pedidoItens;
	}
}
