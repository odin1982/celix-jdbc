package org.celix.service;

import java.util.List;

import org.celix.model.TipoUsuarioModel;

public interface TipoUsuarioService {
	public List<TipoUsuarioModel> findAll();
	public TipoUsuarioModel findById(Long id);
	public List<TipoUsuarioModel> findByDescription(String description);
	public void save(TipoUsuarioModel tipoProducto);
	public void update(TipoUsuarioModel tipoProducto);
	public void deleteById(Long id);
}
