package org.celix.repository;

import java.util.List;
import org.celix.model.TipoUsuarioModel;

public interface TipoUsuarioRepository {
	public List<TipoUsuarioModel> findAll();
	public TipoUsuarioModel findById(Long id);
	public List<TipoUsuarioModel> findByDescription(String description);
	public void save(TipoUsuarioModel tipoProducto);
	public void update(TipoUsuarioModel tipoProducto);
	public void deleteById(Long id);
}
