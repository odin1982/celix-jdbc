package org.celix.repository.impl;

import java.util.List;

import org.celix.model.AlmacenModel;
import org.celix.model.TiendaModel;
import org.celix.repository.TiendaRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class TiendaRepositoryImpl implements TiendaRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public List<TiendaModel> findAll() {
		List<TiendaModel> tiendas = namedJdbcTemplate.query(consultas.getProperty("tienda.find.all"), new BeanPropertyRowMapper<>(TiendaModel.class));
		return tiendas;
	}

	@Override
	public TiendaModel findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("tienda.find.by.id"),namedParameters,new BeanPropertyRowMapper<TiendaModel>(TiendaModel.class));
	}

	@Override
	public List<TiendaModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("tienda.find.by.description"),namedParameters,new BeanPropertyRowMapper<>(TiendaModel.class));

	}

	@Override
	public void save(TiendaModel tienda) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "nombre",tienda.getNombre());
		namedParameters.addValue( "direccion",tienda.getDireccion()); 
		namedParameters.addValue( "telefono_fijo",tienda.getTelefonoFijo()); 
		namedParameters.addValue( "telefono_celular",tienda.getTelefonoCelular()); 
		namedParameters.addValue( "correo_electronico",tienda.getCorreoElectronico());
		namedJdbcTemplate.update(consultas.getProperty("tienda.insert"), namedParameters);
	}

	@Override
	public void update(TiendaModel almacen) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}

}
