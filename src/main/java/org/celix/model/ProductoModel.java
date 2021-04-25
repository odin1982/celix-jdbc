package org.celix.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductoModel{
	private Long id;
	private String nombre;
	private String descripcion;
	private Long idTipoProducto;
	private String descTipoProducto;
	private Long idProveedor;
	private String nombreProveedor;
	private Long idMarca;
	private String descMarca;
	private BigDecimal precioCompra;
	private BigDecimal precioCompraIVA;
	private boolean precioCompraConIVA;
	private BigDecimal precioVenta;
	private String codigoBarrasTienda;
	private String codigoBarrasMarca;
	private LocalDateTime fechaAgrego;
	private Long usuarioAgrego;
	private LocalDateTime fechaModifico;
	private Long usuarioModifico;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Long getIdTipoProducto() {
		return idTipoProducto;
	}
	
	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	public Long getIdMarca() {
		return idMarca;
	}
	
	public void setIdMarca(Long idMarca) {
		this.idMarca = idMarca;
	}
	
	public BigDecimal getPrecioCompra() {
		return precioCompra;
	}
	
	public void setPrecioCompra(BigDecimal precioCompra) {
		this.precioCompra = precioCompra;
	}
	
	public BigDecimal getPrecioVenta() {
		return precioVenta;
	}
	
	public void setPrecioVenta(BigDecimal precioVenta) {
		this.precioVenta = precioVenta;
	}
	
	public String getCodigoBarrasTienda() {
		return codigoBarrasTienda;
	}
	
	public void setCodigoBarrasTienda(String codigoBarrasTienda) {
		this.codigoBarrasTienda = codigoBarrasTienda;
	}
	
	public String getCodigoBarrasMarca() {
		return codigoBarrasMarca;
	}
	
	public void setCodigoBarrasMarca(String codigoBarrasMarca) {
		this.codigoBarrasMarca = codigoBarrasMarca;
	}

	public LocalDateTime getFechaAgrego() {
		return fechaAgrego;
	}

	public void setFechaAgrego(LocalDateTime fechaAgrego) {
		this.fechaAgrego = fechaAgrego;
	}

	public Long getUsuarioAgrego() {
		return usuarioAgrego;
	}

	public void setUsuarioAgrego(Long usuarioAgrego) {
		this.usuarioAgrego = usuarioAgrego;
	}

	public LocalDateTime getFechaModifico() {
		return fechaModifico;
	}

	public void setFechaModifico(LocalDateTime fechaModifico) {
		this.fechaModifico = fechaModifico;
	}

	public Long getUsuarioModifico() {
		return usuarioModifico;
	}

	public void setUsuarioModifico(Long usuarioModifico) {
		this.usuarioModifico = usuarioModifico;
	}

	public String getDescTipoProducto() {
		return descTipoProducto;
	}

	public void setDescTipoProducto(String descTipoProducto) {
		this.descTipoProducto = descTipoProducto;
	}

	public String getNombreProveedor() {
		return nombreProveedor;
	}

	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	public String getDescMarca() {
		return descMarca;
	}

	public void setDescMarca(String descMarca) {
		this.descMarca = descMarca;
	}

	public BigDecimal getPrecioCompraIVA() {
		return precioCompraIVA;
	}

	public void setPrecioCompraIVA(BigDecimal precioCompraIVA) {
		this.precioCompraIVA = precioCompraIVA;
	}
	
	public boolean isPrecioCompraConIVA() {
		return precioCompraConIVA;
	}

	public void setPrecioCompraConIVA(boolean precioCompraConIVA) {
		this.precioCompraConIVA = precioCompraConIVA;
	}

	@Override
	public String toString() {
		return "ProductoModel [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", idTipoProducto="
				+ idTipoProducto + ", descTipoProducto=" + descTipoProducto + ", idProveedor=" + idProveedor
				+ ", nombreProveedor=" + nombreProveedor + ", idMarca=" + idMarca + ", descMarca=" + descMarca
				+ ", precioCompra=" + precioCompra + ", precioCompraIVA=" + precioCompraIVA + ", precioCompraConIVA="
				+ precioCompraConIVA + ", precioVenta=" + precioVenta + ", codigoBarrasTienda=" + codigoBarrasTienda
				+ ", codigoBarrasMarca=" + codigoBarrasMarca + ", fechaAgrego=" + fechaAgrego + ", usuarioAgrego="
				+ usuarioAgrego + ", fechaModifico=" + fechaModifico + ", usuarioModifico=" + usuarioModifico + "]";
	}

}
