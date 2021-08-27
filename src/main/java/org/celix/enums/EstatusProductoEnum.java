package org.celix.enums;

public enum EstatusProductoEnum {
	NUEVO(9);
	
	private EstatusProductoEnum(int id) {
		this.id = id;
	}
	
	private int id;

	public int getId() {
		return id;
	}
	
}
