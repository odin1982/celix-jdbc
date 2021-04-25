package org.celix.repository.impl;

import java.util.List;

import org.celix.model.TipoDocumentoModel;
import org.celix.repository.TipoDocumentoRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;
		
	@Override
	public List<TipoDocumentoModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("tipo.documento.find.all"), new BeanPropertyRowMapper<>(TipoDocumentoModel.class));
	}

	@Override
	public TipoDocumentoModel findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("tipo.documento.find.by.id"),namedParameters,new BeanPropertyRowMapper<>(TipoDocumentoModel.class));
	}
	
	@Override
	public List<TipoDocumentoModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("tipo.documento.find.by.descripcion"),namedParameters,new BeanPropertyRowMapper<>(TipoDocumentoModel.class));
	}

	@Override
	public void save(TipoDocumentoModel tipoDocumento) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",tipoDocumento.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("tipo.documento.insert"), namedParameters);
	}

	@Override
	public void update(TipoDocumentoModel tipoDocumento) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",tipoDocumento.getId() );
		namedParameters.addValue( "descripcion",tipoDocumento.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("tipo.documento.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("tipo.documento.delete"), namedParameters);
	}

}
