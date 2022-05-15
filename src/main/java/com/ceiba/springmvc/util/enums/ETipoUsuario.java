package com.ceiba.springmvc.util.enums;

public enum ETipoUsuario {
	
	AFILIADO(1, "Usuario afiliado"),
	EMPLEADO(2, "Usuario empleado de la biblioteca"),
	INVITADO(3, "Usuario invitado");

    private int id;
    /** */
    private String name;

    ETipoUsuario(int id, String name) {
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