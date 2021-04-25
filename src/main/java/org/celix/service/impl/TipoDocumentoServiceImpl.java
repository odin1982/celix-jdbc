package org.celix.service.impl;

import java.util.List;

import org.celix.model.TipoDocumentoModel;
import org.celix.repository.TipoDocumentoRepository;
import org.celix.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService{
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Override
	public List<TipoDocumentoModel> findAll() {
		return tipoDocumentoRepository.findAll();
	}

	@Override
	public TipoDocumentoModel findById(Long id) {
		return tipoDocumentoRepository.findById(id);
	}

	@Override
	public List<TipoDocumentoModel> findByDescription(String description) {
		return tipoDocumentoRepository.findByDescription(description);
	}

	@Override
	public void save(TipoDocumentoModel tipoProducto) {
		tipoDocumentoRepository.save(tipoProducto);
	}

	@Override
	public void update(TipoDocumentoModel tipoProducto) {
		tipoDocumentoRepository.update(tipoProducto);
	}

	@Override
	public void deleteById(Long id) {
		tipoDocumentoRepository.deleteById(id);
	}

}
