package com.ceiba.springmvc.web;

import com.ceiba.springmvc.service.PrestamoService;
import com.ceiba.springmvc.util.dto.PrestamoDto;
import com.ceiba.springmvc.util.dto.RespuesaErrorPrestamoDto;
import com.github.fge.jsonpatch.JsonPatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/prestamos")
public class PrestamoControlador {

    private static Logger _logger = LoggerFactory.getLogger(PrestamoControlador.class);
    @Autowired
    PrestamoService prestamoService;

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PostMapping()
    public ResponseEntity<?> solicitudPrestamo(@RequestBody(required = true) PrestamoDto to) {
            return new ResponseEntity<>(prestamoService.solicitudPrestarlibro(to),HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("/{prestamoId}")
    public ResponseEntity<?> obtenerPrestamo(@PathVariable Long prestamoId) {
            return new ResponseEntity<>(prestamoService.obtenerPrestamo(prestamoId), HttpStatus.OK);
    }


    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping(path = "/{prestamoId}/entregarLibro")
    public ResponseEntity<?> entregarLibro(@PathVariable (required = true) Long prestamoId) {
            prestamoService.entregarLibro(prestamoId);
            return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @PatchMapping(path = "/{prestamoId}, consumes = \"application/json-patch+json\"")
    public ResponseEntity<?> actualizaionParciaPrestamo(@PathVariable (required = true) Long prestamoId,@RequestBody JsonPatch patch) {
        prestamoService.actualizaionParcialLibro(prestamoId,patch);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", maxAge = 3600)
    @DeleteMapping("/{prestamoId}")
    public ResponseEntity<?> eliminarPrestamo(@PathVariable Long prestamoId) {
        prestamoService.eliminarPrestamo(prestamoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Pasar a Usuario
    @CrossOrigin(origins = "*", maxAge = 3600)
    @GetMapping("api/prestamo/obtenerPrestamosUsuario")
    public ResponseEntity<?> obtenerPrestamosUsuario(@RequestParam(required = true) String identificacionUsuario) {
        return new ResponseEntity<>(prestamoService.obtenerPrestamosUsuario(identificacionUsuario), HttpStatus.OK);
    }

}

