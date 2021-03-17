package org.celix.repository;

import java.util.List;

import org.celix.model.ProductoModel;

public interface ProductoRepository {
	public List<ProductoModel> findAll();
	public ProductoModel findById(Long id);
	public ProductoModel findByMarca(Long idMarca);
	public ProductoModel findByProveedor(Long idProveedor);
	public ProductoModel findByTipoProducto(Long idTipoProducto);
	public List<ProductoModel> findByNombre(String nombre);
	public void deleteById(Long id);
	public void save(ProductoModel producto);
	public void update(ProductoModel producto);
}
