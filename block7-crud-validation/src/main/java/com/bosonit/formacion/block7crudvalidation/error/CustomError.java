package com.bosonit.formacion.block7crudvalidation.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class CustomError {
    private Date timestamp;
    private int HttpCodeNumber;
    private String message;
}
