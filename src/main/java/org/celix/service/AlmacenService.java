package org.celix.service;

import java.util.List;

import org.celix.model.AlmacenModel;

public interface AlmacenService {
	List<AlmacenModel> findAll();
	void deleteById(Long idAlmacen);
	void save(AlmacenModel almacen);
	AlmacenModel findById(Long idAlmacen);
	void update(AlmacenModel almacen);
}
