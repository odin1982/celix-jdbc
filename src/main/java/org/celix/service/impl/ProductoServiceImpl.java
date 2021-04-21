package org.celix.service.impl;

import java.util.List;

import org.celix.model.ProductoModel;
import org.celix.repository.ProductoRepository;
import org.celix.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{ 
	@Autowired
	private ProductoRepository productoRepository;

	@Override
	public List<ProductoModel> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public ProductoModel findById(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public ProductoModel findByMarca(Long idMarca) {
		return productoRepository.findByMarca(idMarca);
	}

	@Override
	public ProductoModel findByProveedor(Long idProveedor) {
		return productoRepository.findByProveedor(idProveedor);
	}

	@Override
	public ProductoModel findByTipoProducto(Long idTipoProducto) {
		return productoRepository.findByTipoProducto(idTipoProducto);
	}

	@Override
	public List<ProductoModel> findByNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}

	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}

	@Override
	public void save(ProductoModel producto) {
		productoRepository.save(producto);
	}

	@Override
	public void update(ProductoModel producto) {
		productoRepository.update(producto);
	}

}
