package org.celix.service.impl;

import java.util.List;

import org.celix.model.MarcaModel;
import org.celix.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements org.celix.service.MarcaService{

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	public List<MarcaModel> findAll() {
		return marcaRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		marcaRepository.deleteById(id);
	}

	@Override
	public void save(MarcaModel marca) {
		marcaRepository.save(marca);
	}

	@Override
	public MarcaModel findById(Long id) {
		return marcaRepository.findById(id);
	}

	@Override
	public void update(MarcaModel marca) {
		marcaRepository.update(marca);
	}

}
