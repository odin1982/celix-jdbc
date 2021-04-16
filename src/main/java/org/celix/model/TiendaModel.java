package org.celix.model;

public class TiendaModel{
	
	private Integer id;
	private String 	nombre;
	private String 	direccion;
	private String 	telefonoFijo;
	private String 	telefonoCelular;
	private String 	correoElectronico;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefonoFijo() {
		return telefonoFijo;
	}
	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	@Override
	public String toString() {
		return "TiendaModel [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + ", telefonoFijo="
				+ telefonoFijo + ", telefonoCelular=" + telefonoCelular + ", correoElectronico=" + correoElectronico
				+ "]";
	}
	
}
