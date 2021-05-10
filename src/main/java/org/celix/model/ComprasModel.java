package org.celix.model;

public class ComprasModel {
	private Long idProveedor;
	private String numeroDocumento;
	private Long idTipoDocumento;
	private Long idAlmacen;
	private String codigoProducto;
	
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
	
}
