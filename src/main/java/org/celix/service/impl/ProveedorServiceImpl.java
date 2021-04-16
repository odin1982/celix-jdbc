package org.celix.service.impl;

import java.util.List;

import org.celix.model.ProveedorModel;
import org.celix.repository.ProveedorRepository;
import org.celix.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorServiceImpl implements ProveedorService{
	@Autowired 
	private ProveedorRepository proveedorRepository; 

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
		proveedorRepository.save(proveedor);
	}

	@Override
	public void update(ProveedorModel proveedor) {
		proveedorRepository.update(proveedor);
	}

	@Override
	public void deleteById(Long id) {
		proveedorRepository.deleteById(id);
	}

}
