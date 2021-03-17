package org.celix.service.impl;

import java.util.List;

import org.celix.model.AlmacenModel;
import org.celix.repository.AlmacenRepository;
import org.celix.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlmacenServiceImpl implements AlmacenService{
	
	@Autowired
	private AlmacenRepository almacenRepository;

	@Override
	public List<AlmacenModel> findAll() {
		List<AlmacenModel> almacenes = almacenRepository.findAll();		
		return almacenes;
	}

	@Override
	public void deleteById(Long id) {
		almacenRepository.deleteById(id);
	}

	@Override
	public void save(AlmacenModel almacen) {
		almacenRepository.save(almacen);
	}

	@Override
	public AlmacenModel findById(Long id) {
		AlmacenModel almacen = almacenRepository.findById(id);
		return almacen;
	}

	@Override
	public void update(AlmacenModel almacen) {
		almacenRepository.update(almacen);
	}
	
	
}
