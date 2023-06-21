package com.bosonit.formacion.block7crudvalidation.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends RuntimeException{
    String message;
    public UnprocessableEntityException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
