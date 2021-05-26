package org.celix.service.impl;

import java.util.List;

import org.celix.model.MarcaModel;
import org.celix.repository.MarcaRepository;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl implements org.celix.service.MarcaService{

	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private MessagesPropertiesConfig messages;
	
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
		if(marca.getDescripcion().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.descripcion"));
		}
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
