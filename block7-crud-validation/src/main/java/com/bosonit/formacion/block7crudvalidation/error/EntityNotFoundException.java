package com.bosonit.formacion.block7crudvalidation.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpStatusCodeException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends HttpStatusCodeException {
    public EntityNotFoundException (){
        super(HttpStatus.NOT_FOUND);
    }
}
