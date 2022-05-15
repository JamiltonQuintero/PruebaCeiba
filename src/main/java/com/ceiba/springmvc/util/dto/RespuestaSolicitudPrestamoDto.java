package com.ceiba.springmvc.util.dto;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
public class RespuestaSolicitudPrestamoDto {

    private Long id;
    private String fechaMaximaDevolucion;

    public RespuestaSolicitudPrestamoDto(Long id, String fechaMaximaDevolucion) {
        this.id = id;
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }

    public RespuestaSolicitudPrestamoDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFechaMaximaDevolucion() {
        return fechaMaximaDevolucion;
    }

    public void setFechaMaximaDevolucion(String fechaMaximaDevolucion) {
        this.fechaMaximaDevolucion = fechaMaximaDevolucion;
    }
}
