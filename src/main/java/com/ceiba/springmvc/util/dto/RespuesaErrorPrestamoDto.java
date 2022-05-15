package com.ceiba.springmvc.util.dto;

/*@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter*/
public class RespuesaErrorPrestamoDto {

    private String mensaje;

    public RespuesaErrorPrestamoDto(String mensaje) {
        this.mensaje = mensaje;
    }

    public RespuesaErrorPrestamoDto() {
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
