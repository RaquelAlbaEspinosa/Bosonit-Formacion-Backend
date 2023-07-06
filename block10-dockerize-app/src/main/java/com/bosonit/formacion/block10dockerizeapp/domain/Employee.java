package com.bosonit.formacion.block10dockerizeapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    String employeeId;
    String employeeName;
    String employeeAddress;
    String employeeEmail;
}
