package com.bosonit.formacion.block7crudvalidation.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<CustomError> handleEntityNotFoundException (EntityNotFoundException ex) {
        CustomError customError = new CustomError(new Date(), 404, "No se ha encontrado ningún usuario con dicho id");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customError);
    }
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<CustomError> handleUnprocessableEntityException (UnprocessableEntityException ex){
        CustomError customError = new CustomError(new Date(), 422, ex.getMessage());
        System.out.println(customError);
        return  ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(customError);
    }
}
