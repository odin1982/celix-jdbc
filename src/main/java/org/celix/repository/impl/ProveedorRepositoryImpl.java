package org.celix.repository.impl;

import java.util.List;
import org.celix.model.ProveedorModel;
import org.celix.repository.ProveedorRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ProveedorRepositoryImpl implements ProveedorRepository{

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;
	
	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public List<ProveedorModel> findAll() {
		return namedJdbcTemplate.query( consultas.getProperty("proveedor.findAll"), new BeanPropertyRowMapper<>(ProveedorModel.class) );
	}

	@Override
	public ProveedorModel findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("proveedor.find.by.id"),namedParameters,new BeanPropertyRowMapper<>(ProveedorModel.class));
	}

	@Override
	public List<ProveedorModel> findByNombre(String nombre) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "nombre",nombre);
		return namedJdbcTemplate.query(consultas.getProperty("proveedor.find.by.descripcion"),namedParameters,new BeanPropertyRowMapper<>(ProveedorModel.class));
	}

	@Override
	public void save(ProveedorModel proveedor) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "nombre",proveedor.getNombre());
		namedParameters.addValue( "direccion",proveedor.getDireccion());
		namedParameters.addValue( "telefono",proveedor.getTelefono());
		namedParameters.addValue( "correoElectronico",proveedor.getCorreoElectronico());
		namedParameters.addValue( "sitioWeb",proveedor.getSitioWeb());
		namedParameters.addValue( "rfc",proveedor.getRfc());
		namedParameters.addValue( "razonSocial",proveedor.getRazonSocial());
		namedJdbcTemplate.update(consultas.getProperty("proveedor.insert"), namedParameters);
	}

	@Override
	public void update(ProveedorModel proveedor) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",proveedor.getId());
		namedParameters.addValue( "nombre",proveedor.getNombre());
		namedParameters.addValue( "direccion",proveedor.getDireccion());
		namedParameters.addValue( "telefono",proveedor.getTelefono());
		namedParameters.addValue( "correoElectronico",proveedor.getCorreoElectronico());
		namedParameters.addValue( "sitioWeb",proveedor.getSitioWeb());
		namedParameters.addValue( "rfc",proveedor.getRfc());
		namedParameters.addValue( "razonSocial",proveedor.getRazonSocial());
		namedJdbcTemplate.update(consultas.getProperty("proveedor.update"), namedParameters);
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue( "id",id );
		namedJdbcTemplate.update(consultas.getProperty("proveedor.delete"), namedParameters);
	}

}
