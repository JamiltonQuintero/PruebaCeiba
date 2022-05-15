package com.ceiba.springmvc.util.enums;


public enum EEstadoUsuario {
	
	ACTIVO(1, "Activo"),
	INACTIVO(2, "Inactivo"),
	PENALIZADO(3, "Penalizado"),
	MAXIMO_LIBROS_PRESTADOS(4, "Maximo libros prestados");

    private int id;
    /** */
    private String name;

	EEstadoUsuario(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}