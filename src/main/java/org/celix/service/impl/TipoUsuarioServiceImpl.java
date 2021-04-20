package org.celix.service.impl;

import java.util.List;

import org.celix.model.TipoUsuarioModel;
import org.celix.repository.TipoUsuarioRepository;
import org.celix.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	@Override
	public List<TipoUsuarioModel> findAll() {
		return tipoUsuarioRepository.findAll();
	}

	@Override
	public TipoUsuarioModel findById(Long id) {
		return tipoUsuarioRepository.findById(id);
	}

	@Override
	public List<TipoUsuarioModel> findByDescription(String description) {
		return tipoUsuarioRepository.findByDescription(description);
	}

	@Override
	public void save(TipoUsuarioModel tipoProducto) {
		tipoUsuarioRepository.save(tipoProducto);
	}

	@Override
	public void update(TipoUsuarioModel tipoProducto) {
		tipoUsuarioRepository.update(tipoProducto);
	}

	@Override
	public void deleteById(Long id) {
		tipoUsuarioRepository.deleteById(id);
	}

}
