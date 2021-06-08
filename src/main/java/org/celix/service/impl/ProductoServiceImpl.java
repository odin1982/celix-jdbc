package org.celix.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.celix.model.ProductoModel;
import org.celix.repository.ProductoRepository;
import org.celix.service.ProductoService;
import org.celix.util.MessagesPropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{ 
	private static final int DOS_DECIMALES = 2;
	
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private MessagesPropertiesConfig messages;

	@Override
	public List<ProductoModel> findAll() {
		return productoRepository.findAll();
	}

	@Override
	public ProductoModel findById(Long id) {
		return productoRepository.findById(id);
	}

	@Override
	public ProductoModel findByMarca(Long idMarca) {
		return productoRepository.findByMarca(idMarca);
	}

	@Override
	public ProductoModel findByProveedor(Long idProveedor) {
		return productoRepository.findByProveedor(idProveedor);
	}

	@Override
	public ProductoModel findByTipoProducto(Long idTipoProducto) {
		return productoRepository.findByTipoProducto(idTipoProducto);
	}

	@Override
	public List<ProductoModel> findByNombre(String nombre) {
		return productoRepository.findByNombre(nombre);
	}

	@Override
	public void deleteById(Long id) {
		productoRepository.deleteById(id);
	}

	@Override
	public void save(ProductoModel producto) {
		validarCampos(producto);
		productoRepository.save(this.asignarPrecio(producto));
	}

	@Override
	public void update(ProductoModel producto) {
		validarCampos(producto);
		productoRepository.update(this.asignarPrecio(producto));
	}
	
	private ProductoModel asignarPrecio(ProductoModel producto) {
		if(producto.isPrecioCompraConIVA()) {
			producto.setPrecioCompra(producto.getPrecioCompraIVA().divide(new BigDecimal(1.16), DOS_DECIMALES,RoundingMode.HALF_UP));
		}else {
			producto.setPrecioCompraIVA(producto.getPrecioCompra().multiply(new BigDecimal(1.16)).setScale(DOS_DECIMALES, RoundingMode.HALF_UP));
		}
		
		return producto;
	}
	
	private void validarCampos(ProductoModel producto) {
		if(producto.getNombre().trim().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.nombre"));
		}
		
		if(producto.getDescripcion().trim().isEmpty()) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.descripcion"));
		}
		
		if(producto.getIdTipoProducto().longValue() == 0) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.tipo.producto"));
		}
		
		if(producto.getIdMarca() == 0) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.marca"));
		}
		
		if(producto.getIdProveedor().longValue() == 0) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.proveedor"));
		}
		
		if(!producto.isPrecioCompraConIVA() && producto.getPrecioCompra().doubleValue() == 0) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.precio.compra"));
		}
		
		if(producto.isPrecioCompraConIVA() && producto.getPrecioCompraIVA().longValue() == 0 ) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.precio.iva"));
		}
		
		if(producto.getPrecioVenta().doubleValue() == 0) {
			throw new IllegalArgumentException(messages.getProperty("celix.exceptions.argumento.invalido.precio.venta"));
		}

	}

	@Override
	public boolean existeCodigo(String codigoProducto) {
		return productoRepository.existeCodigo(codigoProducto);
	}

	@Override
	public ProductoModel findByCodigoProducto(String codigoProducto) {
		return productoRepository.findByCodigoProducto(codigoProducto);
	}

}
