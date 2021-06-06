package org.celix.service.impl;

import java.util.List;

import org.celix.model.ProveedorModel;
import org.celix.repository.ProveedorRepository;
import org.celix.service.ProveedorService;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired 
	private ProveedorRepository proveedorRepository; 
	
	@Autowired
	private MessagesPropertiesConfig messages;

	@Override
	public List<ProveedorModel> findAll() {
		return proveedorRepository.findAll();
	}

	@Override
	public ProveedorModel findById(Long id) {
		return proveedorRepository.findById(id);
	}

	@Override
	public List<ProveedorModel> findByNombre(String nombre) {
		return proveedorRepository.findByNombre(nombre);
	}

	@Override
	public void save(ProveedorModel proveedor) {
		validarCampos(proveedor);
			
		proveedorRepository.save(proveedor);
	}


	@Override
	public void update(ProveedorModel proveedor) {
		validarCampos(proveedor);
		proveedorRepository.update(proveedor);
	}

	@Override
	public void deleteById(Long id) {
		proveedorRepository.deleteById(id);
	}

	private void validarCampos(ProveedorModel proveedor) {
		if( proveedor.getNombre().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.nombre"));
		}
		
		if( proveedor.getDireccion().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.direccion"));
		}
		
		if( proveedor.getTelefono().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.telefono"));
		}
		
		if( proveedor.getRfc().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.rfc"));
		}
		
		if( proveedor.getRazonSocial().trim().isEmpty() ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.razon.social"));
		}
	}
}
