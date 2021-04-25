package org.celix.service;

import java.util.List;

import org.celix.model.TipoDocumentoModel;

public interface TipoDocumentoService {
	public List<TipoDocumentoModel> findAll();
	public TipoDocumentoModel findById(Long id);
	public List<TipoDocumentoModel> findByDescription(String description);
	public void save(TipoDocumentoModel tipoProducto);
	public void update(TipoDocumentoModel tipoProducto);
	public void deleteById(Long id);
}
