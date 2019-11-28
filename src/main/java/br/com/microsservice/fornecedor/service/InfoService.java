package br.com.microsservice.fornecedor.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.microsservice.fornecedor.model.InfoFornecedor;
import br.com.microsservice.fornecedor.repository.InfoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InfoService {

	private Logger logger = LoggerFactory.getLogger(InfoService.class);
	 
	
	@Autowired
	private InfoRepository infoRepository;
	
	public InfoFornecedor getInfoPorEstado(String estado) {
		logger.info("Buscando informações dos fornecedores por estado {}", estado);
		return this.infoRepository.findByEstado(estado);
	}
}
