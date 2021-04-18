package org.celix.service.impl;

import java.util.List;

import org.celix.model.TipoProductoModel;
import org.celix.repository.TipoProductoRepository;
import org.celix.service.TipoProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoProductoServiceImpl implements TipoProductoService{
	
	@Autowired
	private TipoProductoRepository tipoProductoRepository;

	@Override
	public List<TipoProductoModel> findAll() {
		return tipoProductoRepository.findAll();
	}

	@Override
	public TipoProductoModel findById(Long id) {
		return tipoProductoRepository.findById(id);
	}

	@Override
	public List<TipoProductoModel> findByDescription(String description) {
		return tipoProductoRepository.findByDescription(description);
	}

	@Override
	public void save(TipoProductoModel tipoProducto) {
		tipoProductoRepository.save(tipoProducto);
	}

	@Override
	public void update(TipoProductoModel tipoProducto) {
		tipoProductoRepository.update(tipoProducto);
	}

	@Override
	public void deleteById(Long id) {
		tipoProductoRepository.deleteById(id);
	}

}
