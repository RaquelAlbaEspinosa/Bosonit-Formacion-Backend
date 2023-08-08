package com.bosonit.formacion.block7crudvalidation.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class UnprocessableEntityException extends HttpStatusCodeException {
    private final String message;
    public UnprocessableEntityException(String message){
        super(HttpStatus.UNPROCESSABLE_ENTITY);
        this.message = message;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
