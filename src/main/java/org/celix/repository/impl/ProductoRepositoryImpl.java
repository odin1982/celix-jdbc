package org.celix.repository.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.celix.model.ProductoModel;
import org.celix.repository.ProductoRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepositoryImpl implements ProductoRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public List<ProductoModel> findAll() {
		return namedJdbcTemplate.query(consultas.getProperty("producto.find.all"),
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
	public boolean existeCodigo(String codigoProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("codigoProducto", codigoProducto);
		Integer cantidadDeCodigos = namedJdbcTemplate.queryForObject(consultas.getProperty("producto.existe"),namedParameters, Integer.class);
		if(cantidadDeCodigos > 0) {
			return true;
		}else {
			return false;
		}
		 
	}
	
	@Override
	public ProductoModel findByCodigoProducto(String codigoProducto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("codigoProducto", codigoProducto);
		return namedJdbcTemplate.queryForObject(consultas.getProperty("producto.find.by.codigo.producto"),namedParameters, new BeanPropertyRowMapper<>(ProductoModel.class));
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
		namedParameters.addValue("precioCompraIVA", producto.getPrecioCompraIVA());
		namedParameters.addValue("precioVenta", producto.getPrecioVenta());
		namedParameters.addValue("codigoBarrasTienda", producto.getCodigoBarrasTienda());
		namedParameters.addValue("codigoBarrasMarca", producto.getCodigoBarrasTienda());
		namedParameters.addValue("idProveedor", producto.getIdProveedor());
		namedParameters.addValue("idMarca", producto.getIdMarca());
		namedParameters.addValue("nombre", producto.getNombre());
		namedParameters.addValue("fechaAgrego", LocalDateTime.now());
		//TODO Se debe agregar el usuario en Sesión con Spring Security
		namedParameters.addValue("usuarioAgrego", 1);
		namedJdbcTemplate.update(consultas.getProperty("producto.insert"), namedParameters);
	}

	@Override
	public void update(ProductoModel producto) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", producto.getId());
		namedParameters.addValue("descripcion", producto.getDescripcion());
		namedParameters.addValue("idTipoProducto", producto.getIdTipoProducto());
		namedParameters.addValue("precioCompra", producto.getPrecioCompra());
		namedParameters.addValue("precioCompraIVA", producto.getPrecioCompraIVA());
		namedParameters.addValue("precioVenta", producto.getPrecioVenta());
		namedParameters.addValue("codigoBarrasTienda", producto.getCodigoBarrasTienda());
		namedParameters.addValue("codigoBarrasMarca", producto.getCodigoBarrasMarca());
		namedParameters.addValue("idProveedor", producto.getIdProveedor());
		namedParameters.addValue("idMarca", producto.getIdMarca());
		namedParameters.addValue("nombre", producto.getNombre());
		namedParameters.addValue("fechaModifico", LocalDateTime.now());
		//TODO Se debe agregar el usuario en Sesión con Spring Security
		namedParameters.addValue("usuarioModifico",1);
		namedJdbcTemplate.update(consultas.getProperty("producto.update"), namedParameters);
	}
}
