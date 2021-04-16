package org.celix.repository;

import java.util.List;

import org.celix.model.TiendaModel;

public interface TiendaRepository {
	public List<TiendaModel> findAll();
	public TiendaModel findById(Long id);
	public List<TiendaModel> findByNombre(String nombre);
	public void save(TiendaModel almacen);
	public void update(TiendaModel almacen);
	public void deleteById(Long id);
}
