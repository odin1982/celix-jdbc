package org.celix.service.impl;

import java.util.List;

import org.celix.model.ProveedorModel;
import org.celix.model.TiendaModel;
import org.celix.repository.TiendaRepository;
import org.celix.service.TiendaService;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaServiceImpl implements TiendaService{
	@Autowired
	private TiendaRepository tiendaRepository;
	
	@Autowired
	private MessagesPropertiesConfig messages;

	@Override
	public List<TiendaModel> findAll() {
		return tiendaRepository.findAll();
	}

	@Override
	public TiendaModel findById(Long id) {
		return tiendaRepository.findById(id);
	}

	@Override
	public List<TiendaModel> findByNombre(String nombre) {
		return tiendaRepository.findByNombre(nombre);
	}

	@Override
	public void save(TiendaModel tienda) {
		validarCampos(tienda);
		tiendaRepository.save(tienda);
	}

	@Override
	public void update(TiendaModel tienda) {
		validarCampos(tienda);
		tiendaRepository.update(tienda);
	}

	@Override
	public void deleteById(Long id) {
		tiendaRepository.deleteById(id);
	}
	
	private void validarCampos(TiendaModel tienda) {
		if( tienda.getNombre().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.nombre"));
		}
		
		if( tienda.getDireccion().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.direccion"));
		}
	}

}
