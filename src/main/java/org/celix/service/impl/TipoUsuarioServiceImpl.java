package org.celix.service.impl;

import java.util.List;

import org.celix.model.TipoUsuarioModel;
import org.celix.repository.TipoUsuarioRepository;
import org.celix.service.TipoUsuarioService;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService{
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@Autowired
	private MessagesPropertiesConfig messages;

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
	public void save(TipoUsuarioModel tipoUsuario) {
		if(tipoUsuario.getDescripcion().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.descripcion"));
		}
		tipoUsuarioRepository.save(tipoUsuario);
	}

	@Override
	public void update(TipoUsuarioModel tipoUsuario) {
		if(tipoUsuario.getDescripcion().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.descripcion"));
		}
		tipoUsuarioRepository.update(tipoUsuario);
	}

	@Override
	public void deleteById(Long id) {
		tipoUsuarioRepository.deleteById(id);
	}

}
