package com.ceiba.springmvc.advice;

import com.ceiba.springmvc.util.exception.EmptyInputException;
import com.ceiba.springmvc.util.exception.PrestamoException;
import com.ceiba.springmvc.util.exception.UsuarioException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyControllerAdvice {

    @ExceptionHandler(EmptyInputException.class)
    public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
        return new ResponseEntity<String>(emptyInputException.getErrorMessage(), emptyInputException.getErrorCode());
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<String> handleUsuario(UsuarioException usuarioException){
        return new ResponseEntity<String>(usuarioException.getErrorMessage(), usuarioException.getErrorCode());
    }

    @ExceptionHandler(PrestamoException.class)
    public ResponseEntity<String> handleUsuario(PrestamoException prestamoException){
        return new ResponseEntity<String>(prestamoException.getErrorMessage(), prestamoException.getErrorCode());
    }

}
