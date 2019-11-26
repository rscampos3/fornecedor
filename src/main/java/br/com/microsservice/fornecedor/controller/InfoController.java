package br.com.microsservice.fornecedor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microsservice.fornecedor.model.InfoFornecedor;
import br.com.microsservice.fornecedor.service.InfoService;

@RestController
@RequestMapping("/info")
public class InfoController {

	@Autowired
	private InfoService infoService;

	@RequestMapping(value = "/estado/{uf}")
	public ResponseEntity<InfoFornecedor> getInfoPorEstado(@PathVariable("uf") String uf) {
		return new ResponseEntity<InfoFornecedor>(this.infoService.getInfoPorEstado(uf), HttpStatus.OK);
	}

}
