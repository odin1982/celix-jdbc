package org.celix.repository.impl;

import java.util.List;
import org.celix.model.EstatusProductoModel;
import org.celix.repository.EstatusProductoRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EstatusProductoRepositoryImpl implements EstatusProductoRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;
	
	@Override
	public List<EstatusProductoModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("estatus.producto.find.all"),  new BeanPropertyRowMapper<>(EstatusProductoModel.class));
	}

	@Override
	public EstatusProductoModel findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("estatus.producto.find.by.id"), namedParameters,new BeanPropertyRowMapper<EstatusProductoModel>(EstatusProductoModel.class));
	}

	@Override
	public List<EstatusProductoModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("estatus.producto.find.by.description"),namedParameters,new BeanPropertyRowMapper<>(EstatusProductoModel.class));
	}

	@Override
	public void save(EstatusProductoModel estatusProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",estatusProducto.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("estatus.producto.insert"), namedParameters);
	}

	@Override
	public void update(EstatusProductoModel estatusProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",estatusProducto.getId() );
		namedParameters.addValue( "descripcion",estatusProducto.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("estatus.producto.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("estatus.producto.delete"), namedParameters);	
	}
}
