package org.celix.repository.impl;

import java.util.List;
import org.celix.model.AlmacenModel;
import org.celix.repository.AlmacenRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AlmacenRepositoryImpl implements AlmacenRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;
	
	@Override
	public List<AlmacenModel> findAll() {
		try {
			List<AlmacenModel> almacenes = namedJdbcTemplate.query(consultas.getProperty("almacen.find.all"), new BeanPropertyRowMapper<>(AlmacenModel.class));
			return almacenes;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public AlmacenModel findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("almacen.find.by.id"),namedParameters,new BeanPropertyRowMapper<AlmacenModel>(AlmacenModel.class));
	}
	
	@Override
	public List<AlmacenModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("almacen.findByDescription"),namedParameters,new BeanPropertyRowMapper<>(AlmacenModel.class));
	}

	@Override
	public void save(AlmacenModel almacen) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",almacen.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("almacen.insert"), namedParameters);
	}

	@Override
	public void update(AlmacenModel almacen) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",almacen.getId() );
		namedParameters.addValue( "descripcion",almacen.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("almacen.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("almacen.delete"), namedParameters);
	}

}
