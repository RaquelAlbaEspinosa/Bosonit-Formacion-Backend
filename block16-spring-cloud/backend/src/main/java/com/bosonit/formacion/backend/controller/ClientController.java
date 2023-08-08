package com.bosonit.formacion.backend.controller;

import com.bosonit.formacion.backend.application.ClientService;
import com.bosonit.formacion.backend.controller.dto.ClientInputDto;
import com.bosonit.formacion.backend.controller.dto.ClientOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping("/add")
    ClientOutputDto addClient (@RequestBody ClientInputDto clientInputDto){
        return clientService.addClient(clientInputDto);
    }
    @GetMapping("/{id}")
    ClientOutputDto getClientById (@PathVariable String id){
        return clientService.getClientById(id);
    }
    @GetMapping("/all")
    Iterable<ClientOutputDto> getAllClient (@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "4") int pageSize){
        return clientService.getAllClient(pageNumber,pageSize);
    }
    @PutMapping("/update/{id}")
    ClientOutputDto updateClient (@PathVariable String id,
                                  @RequestBody ClientInputDto clientInputDto){
        return clientService.updateClient(id, clientInputDto);
    }
    @DeleteMapping("/delete/{id}")
    String deleteClient (@PathVariable String id){
        clientService.deleteClientById(id);
        return "Se ha eliminado al cliente con id: " + id;
    }
}
