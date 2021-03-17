package org.celix.repository;

import java.util.List;

import org.celix.model.AlmacenModel;

public interface AlmacenRepository {
	public List<AlmacenModel> findAll();
	public AlmacenModel findById(Long id);
	public List<AlmacenModel> findByDescription(String description);
	public void save(AlmacenModel almacen);
	public void update(AlmacenModel almacen);
	public void deleteById(Long id);
}
