package org.celix.model;

public class EstatusProductoModel {
	private Long id;
	private String descripcion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return "EstatusProductoModel [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
}
