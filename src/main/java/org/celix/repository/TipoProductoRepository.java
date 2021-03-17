package org.celix.repository;

import java.util.List;
import org.celix.model.TipoProductoModel;

public interface TipoProductoRepository {
	public List<TipoProductoModel> findAll();
	public TipoProductoModel findById(Long id);
	public List<TipoProductoModel> findByDescription(String description);
	public void save(TipoProductoModel tipoProducto);
	public void update(TipoProductoModel tipoProducto);
	public void deleteById(Long id);
}
