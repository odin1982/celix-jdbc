package org.celix.service;

import java.util.List;

import org.celix.model.ProductoModel;

public interface ProductoService {
	public List<ProductoModel> findAll();
	public ProductoModel findById(Long id);
	public ProductoModel findByMarca(Long idMarca);
	public ProductoModel findByProveedor(Long idProveedor);
	public ProductoModel findByTipoProducto(Long idTipoProducto);
	public List<ProductoModel> findByNombre(String nombre);
	public void deleteById(Long id);
	public void save(ProductoModel producto);
	public void update(ProductoModel producto);
	public boolean existeCodigo(String codigoProducto);
	public ProductoModel findByCodigoProducto(String codigoProducto);
}
