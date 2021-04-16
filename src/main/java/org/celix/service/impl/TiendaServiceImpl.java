package org.celix.service.impl;

import java.util.List;

import org.celix.model.TiendaModel;
import org.celix.repository.TiendaRepository;
import org.celix.service.TiendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TiendaServiceImpl implements TiendaService{
	@Autowired
	private TiendaRepository tiendaRepository;

	@Override
	public List<TiendaModel> findAll() {
		return tiendaRepository.findAll();
	}

	@Override
	public TiendaModel findById(Long id) {
		return tiendaRepository.findById(id);
	}

	@Override
	public List<TiendaModel> findByNombre(String nombre) {
		return tiendaRepository.findByNombre(nombre);
	}

	@Override
	public void save(TiendaModel almacen) {
		tiendaRepository.save(almacen);
	}

	@Override
	public void update(TiendaModel almacen) {
		tiendaRepository.update(almacen);
	}

	@Override
	public void deleteById(Long id) {
		tiendaRepository.deleteById(id);
	}

}
