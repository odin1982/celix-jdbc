package org.celix.repository;

import java.util.List;

import org.celix.model.EstatusProductoModel;

public interface EstatusProductoRepository {
	public List<EstatusProductoModel> findAll();
	public EstatusProductoModel findById(Long id);
	public List<EstatusProductoModel> findByDescription(String description);
	public void save(EstatusProductoModel estatusProducto);
	public void update(EstatusProductoModel estatusProducto);
	public void deleteById(Long id);
}
