package com.ceiba.springmvc.util.enums;

public enum EEstadoPrestamo {

    ACTIVO(1, "Activo"),
    FINALIZADO(2, "Finalizado"),
    PENALIZADO(3, "Penalizado");

    EEstadoPrestamo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    private int id;
    /** */
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
