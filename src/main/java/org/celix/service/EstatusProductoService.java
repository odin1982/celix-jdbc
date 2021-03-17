package org.celix.service;

import java.util.List;

import org.celix.model.EstatusProductoModel;

public interface EstatusProductoService {
	List<EstatusProductoModel> findAll();
	void deleteById(Long id);
	void save(EstatusProductoModel estatusProducto);
	EstatusProductoModel findById(Long id);
	void update(EstatusProductoModel estatusProducto);
}
