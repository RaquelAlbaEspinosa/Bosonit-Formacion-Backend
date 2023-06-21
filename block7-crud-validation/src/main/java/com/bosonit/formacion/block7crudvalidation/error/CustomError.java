package com.bosonit.formacion.block7crudvalidation.error;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CustomError {
    Date timestamp;
    int HttpCode;
    String message;
}
