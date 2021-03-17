package org.celix.repository.impl;

import java.util.List;
import org.celix.model.MarcaModel;
import org.celix.repository.MarcaRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MarcaRepositoryImpl implements MarcaRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;
	
	@Override
	public List<MarcaModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("marca.find.all"), new BeanPropertyRowMapper<>(MarcaModel.class));
	}

	@Override
	public MarcaModel findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("marca.find.by.id"),namedParameters,new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class));
	}
	
	@Override
	public List<MarcaModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("marca.find.by.description"),namedParameters,new BeanPropertyRowMapper<MarcaModel>(MarcaModel.class));
	}

	@Override
	public void save(MarcaModel marca) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",marca.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("marca.insert"), namedParameters);
	}

	@Override
	public void update(MarcaModel marca) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",marca.getId() );
		namedParameters.addValue( "descripcion",marca.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("marca.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("marca.delete"), namedParameters);
	}

}
