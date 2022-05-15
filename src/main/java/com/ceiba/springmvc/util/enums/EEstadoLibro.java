package com.ceiba.springmvc.util.enums;

public enum EEstadoLibro {

	DISPONIBLE(1, "Prestado"),
	PRESTADO(2, "Prestadp"),
	ARCHIVADO(3, "Archivado"),
	DETERIORADO(4, "Deteriorado");

    private int id;
    /** */
    private String name;


	EEstadoLibro(int id, String name) {
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