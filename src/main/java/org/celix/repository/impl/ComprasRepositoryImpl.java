package org.celix.repository.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.celix.enums.EstatusProductoEnum;
import org.celix.model.ComprasModel;
import org.celix.model.ProductoModel;
import org.celix.repository.ComprasRepository;
import org.celix.util.ConsultasPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ComprasRepositoryImpl implements ComprasRepository{
	@Autowired
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	@Autowired
	private ConsultasPropertiesConfig consultas;

	@Override
	public void saveCompra(ComprasModel compras) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		namedParameters.addValue("numeroDocumento", compras.getNumeroDocumento());
		namedParameters.addValue("tipoDocumento", compras.getIdTipoDocumento());
		namedParameters.addValue("idProveedor", compras.getIdProveedor() );
		namedParameters.addValue("fechaCompra", compras.getFechaCompra());
		namedJdbcTemplate.update(consultas.getProperty("inventario.insert"), namedParameters,holder);
		long idInventario = holder.getKey().longValue();
		
//		compras.getProductos()
//				.stream()
//				.forEach(p -> {
//					MapSqlParameterSource namedParametersProducto = new MapSqlParameterSource();
//					namedParametersProducto.addValue("idInventario", idInventario);
//					namedParametersProducto.addValue("idProducto", p.getId());
//					namedParametersProducto.addValue("idEstatusProducto", EstatusProductoEnum.NUEVO.getId());
//					namedParameters.addValue("idAlmacen", compras.getIdAlmacen());
//					namedParametersProducto.addValue("precioCompra",p.getPrecioCompra() );
//					namedParametersProducto.addValue("precioCompraIva", p.getPrecioCompraIVA());
//					namedJdbcTemplate.update(consultas.getProperty("detalle.inventario.producto.insert"), namedParameters);
//				});
		
		List<Map<String, Object>> batchValues = new ArrayList<>(compras.getProductos().size());
		for (ProductoModel p : compras.getProductos()) {
			for(int i=1; i<=p.getCantidad();i++) {
				MapSqlParameterSource namedParametersProducto = new MapSqlParameterSource();
				namedParametersProducto.addValue("idInventario", idInventario);
				namedParametersProducto.addValue("idProducto", p.getId());
				namedParametersProducto.addValue("idEstatusProducto", EstatusProductoEnum.NUEVO.getId());
				namedParametersProducto.addValue("idAlmacen", compras.getIdAlmacen());
				namedParametersProducto.addValue("precioCompra",p.getPrecioCompra() );
				namedParametersProducto.addValue("precioCompraIva", p.getPrecioCompraIVA());
			    batchValues.add(namedParametersProducto.getValues());				
			}
		}
		int[] updateCounts = namedJdbcTemplate.batchUpdate(consultas.getProperty("detalle.inventario.producto.insert"),batchValues.toArray(new Map[compras.getProductos().size()]));
		 
	}
}
