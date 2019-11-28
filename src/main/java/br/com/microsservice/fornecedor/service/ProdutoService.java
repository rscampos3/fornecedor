package br.com.microsservice.fornecedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microsservice.fornecedor.model.Produto;
import br.com.microsservice.fornecedor.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> getProdutosPorEstado(String estado) {
		log.info("Buscando produtos por estado, {}", estado);
		return produtoRepository.findByEstado(estado);
	}

	
}
