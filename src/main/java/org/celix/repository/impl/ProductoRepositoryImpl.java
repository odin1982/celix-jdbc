package org.celix.repository.impl;

import java.util.List;

import org.celix.model.ProductoModel;
import org.celix.repository.ProductoRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class ProductoRepositoryImpl implements ProductoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public List<ProductoModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("producto.findAll"),
				new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public ProductoModel findById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("producto.find.by.id"), namedParameters,
				new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public ProductoModel findByMarca(Long idMarca) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idMarca", idMarca);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("producto.find.by.marca"), namedParameters,
				new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public ProductoModel findByProveedor(Long idProveedor) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idProveedor", idProveedor);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("producto.find.by.proveedor"), namedParameters,
				new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public ProductoModel findByTipoProducto(Long idTipoProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("idTipoProducto", idTipoProducto);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("producto.find.by.tipo.producto"),
				namedParameters, new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public List<ProductoModel> findByNombre(String nombre) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("nombre", nombre);
		return namedJdbcTemplate.query(consultas.getProperty("producto.find.by.nombre"), namedParameters,
				new BeanPropertyRowMapper<>(ProductoModel.class));
	}

	@Override
	public void deleteById(Long id) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		namedJdbcTemplate.update(consultas.getProperty("producto.delete"), namedParameters);
	}

	@Override
	public void save(ProductoModel producto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("descripcion", producto.getDescripcion());
		namedParameters.addValue("idTipoProducto", producto.getIdTipoProducto());
		namedParameters.addValue("precioCompra", producto.getPrecioCompra());
		namedParameters.addValue("precioVenta", producto.getPrecioVenta());
		namedParameters.addValue("codigoDeBarrasTienda", producto.getCodigoBarrasTienda());
		namedParameters.addValue("idProveedor", producto.getIdProveedor());
		namedParameters.addValue("idMarca", producto.getIdMarca());
		namedParameters.addValue("nombre", producto.getNombre());
		namedParameters.addValue("fechaAgrego", producto.getFechaAgrego());
		namedParameters.addValue("usuarioAgrego", producto.getUsuarioAgrego());
		namedJdbcTemplate.update(consultas.getProperty("producto.insert"), namedParameters);
	}

	@Override
	public void update(ProductoModel producto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("descripcion", producto.getDescripcion());
		namedParameters.addValue("idtTipoProducto", producto.getIdTipoProducto());
		namedParameters.addValue("precioCompra", producto.getPrecioCompra());
		namedParameters.addValue("precioVenta", producto.getPrecioVenta());
		namedParameters.addValue("codigoDeBarrasTienda", producto.getCodigoBarrasTienda());
		namedParameters.addValue("idProveedor", producto.getIdProveedor());
		namedParameters.addValue("idMarca", producto.getIdMarca());
		namedParameters.addValue("nombre", producto.getNombre());
		namedParameters.addValue("fechaAgrego", producto.getFechaAgrego());
		namedParameters.addValue("usuarioAgrego", producto.getUsuarioAgrego());
		namedParameters.addValue("fechaModifico", producto.getFechaModifico());
		namedParameters.addValue("usuarioModifico", producto.getUsuarioModifico());
		namedJdbcTemplate.update(consultas.getProperty("producto.update"), namedParameters);
	}
}
