package org.celix.service;

import java.util.List;

import org.celix.model.ProveedorModel;

public interface ProveedorService {
	public List<ProveedorModel> findAll();
	public ProveedorModel findById(Long id);
	public  List<ProveedorModel> findByNombre(String nombre);
	public void save(ProveedorModel proveedor);
	public void update(ProveedorModel proveedor);
	public void deleteById(Long id);
}
