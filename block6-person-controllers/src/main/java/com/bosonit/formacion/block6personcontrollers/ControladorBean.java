package com.bosonit.formacion.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador/bean")
public class ControladorBean {
    private Persona persona1;
    private Persona persona2;
    private Persona persona3;
    public ControladorBean (@Qualifier("bean1") Persona persona1, @Qualifier("bean2") Persona persona2, @Qualifier("bean3") Persona persona3){
        this.persona1 = persona1;
        this.persona2 = persona2;
        this.persona3 = persona3;
    }
    @GetMapping("/{bean}")
    public Persona getPersonaBean (@PathVariable("bean") String bean){
        Persona persona = null;
       if(bean.equals("bean1")){
           persona = this.persona1;
       } else if(bean.equals("bean2")){
           persona = this.persona2;
       } else if(bean.equals("bean3")){
           persona = this.persona3;
       } else {
           System.out.println("Ese bean no existe");
       }
        return persona;
    }
}
