package org.celix.service.impl;

import org.celix.model.ComprasModel;
import org.celix.repository.ComprasRepository;
import org.celix.service.ComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComprasServiceImpl implements ComprasService{
	@Autowired private ComprasRepository comprasRepository;

	@Override
	public void saveCompra(ComprasModel compra) {
		comprasRepository.saveCompra(compra);
	}

}
