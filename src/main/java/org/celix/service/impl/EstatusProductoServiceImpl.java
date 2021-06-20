package org.celix.service.impl;

import java.util.List;

import org.celix.model.EstatusProductoModel;
import org.celix.repository.EstatusProductoRepository;
import org.celix.service.EstatusProductoService;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatusProductoServiceImpl implements EstatusProductoService{

	@Autowired
	private EstatusProductoRepository estatusProductoRepository;
	
	@Autowired
	private MessagesPropertiesConfig messages;
	
	@Override
	public List<EstatusProductoModel> findAll() {
		return estatusProductoRepository.findAll();
	}
	
	public void deleteById(Long id) {
		estatusProductoRepository.deleteById(id);
	}

	@Override
	public void save(EstatusProductoModel estatusProducto) {
		validate(estatusProducto);
		estatusProductoRepository.save(estatusProducto);
	}


	@Override
	public EstatusProductoModel findById(Long id) {
		return estatusProductoRepository.findById(id);
	}

	@Override
	public void update(EstatusProductoModel estatusProducto) {
		validate(estatusProducto);
		estatusProductoRepository.update(estatusProducto);
	}

	private void validate(EstatusProductoModel estatusProducto) {
		if(estatusProducto.getDescripcion().trim().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.descripcion"));
		}
	}
}
