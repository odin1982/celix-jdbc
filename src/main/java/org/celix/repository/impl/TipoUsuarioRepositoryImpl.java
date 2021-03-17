package org.celix.repository.impl;

import java.util.List;
import org.celix.model.TipoUsuarioModel;
import org.celix.repository.TipoUsuarioRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository{
	
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;
		
	@Override
	public List<TipoUsuarioModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("tipo.usuario.find.all"), new BeanPropertyRowMapper<>(TipoUsuarioModel.class));
	}

	@Override
	public TipoUsuarioModel findById(Long id){
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("tipo.usuario.find.by.id"),namedParameters,new BeanPropertyRowMapper<>(TipoUsuarioModel.class));
	}
	
	@Override
	public List<TipoUsuarioModel> findByDescription(String description) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",description);
		return namedJdbcTemplate.query(consultas.getProperty("tipo.usuario.find.by.descripcion"),namedParameters,new BeanPropertyRowMapper<>(TipoUsuarioModel.class));
	}

	@Override
	public void save(TipoUsuarioModel tipoUsuario) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "descripcion",tipoUsuario.getDescripcion());
		namedJdbcTemplate.update(consultas.getProperty("tipo.usuario.insert"), namedParameters);
	}

	@Override
	public void update(TipoUsuarioModel tipoUsuario) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",tipoUsuario.getId() );
		namedParameters.addValue( "descripcion",tipoUsuario.getDescripcion() );
		namedJdbcTemplate.update(consultas.getProperty("tipo.usuario.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("tipo.usuario.delete"), namedParameters);
	}

}
