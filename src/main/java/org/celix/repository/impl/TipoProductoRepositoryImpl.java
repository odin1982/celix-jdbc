package org.celix.repository.impl;

import java.util.List;
import org.celix.model.TipoProductoModel;
import org.celix.repository.TipoProductoRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TipoProductoRepositoryImpl implements TipoProductoRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;
		
	@Override
	public List<TipoProductoModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("tipo.producto.find.all"), new BeanPropertyRowMapper<>(TipoProductoModel.class));
	}

	@Override
	public TipoProductoModel findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("tipo.producto.find.by.id"),namedParameters,new BeanPropertyRowMapper<>(TipoProductoModel.class));
	}
	
	@Override
	public List<TipoProductoModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("tipo.producto.find.by.descripcion"),namedParameters,new BeanPropertyRowMapper<>(TipoProductoModel.class));
	}

	@Override
	public void save(TipoProductoModel tipoProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",tipoProducto.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("tipo.producto.insert"), namedParameters);
	}

	@Override
	public void update(TipoProductoModel tipoProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",tipoProducto.getId() );
		namedParameters.addValue( "descripcion",tipoProducto.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("tipo.producto.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("tipo.producto.delete"), namedParameters);
	}

}
