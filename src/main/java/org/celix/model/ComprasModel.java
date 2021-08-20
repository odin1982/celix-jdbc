package org.celix.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ComprasModel {
	private Long idProveedor;
	private String numeroDocumento;
	private Long idTipoDocumento;
	private Long idAlmacen;
	private String codigoProducto;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaCompra;
	private List<ProductoModel> productos;
	
	public Long getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public Long getIdAlmacen() {
		return idAlmacen;
	}
	public void setIdAlmacen(Long idAlmacen) {
		this.idAlmacen = idAlmacen;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	public List<ProductoModel> getProductos() {
		return productos;
	}
	public void setProductos(List<ProductoModel> productos) {
		this.productos = productos;
	}
	@Override
	public String toString() {
		return "ComprasModel [idProveedor=" + idProveedor + ", numeroDocumento=" + numeroDocumento
				+ ", idTipoDocumento=" + idTipoDocumento + ", idAlmacen=" + idAlmacen + ", codigoProducto="
				+ codigoProducto + ", fechaCompra=" + fechaCompra + ", productos=" + productos + "]";
	}
	
	
	
}
