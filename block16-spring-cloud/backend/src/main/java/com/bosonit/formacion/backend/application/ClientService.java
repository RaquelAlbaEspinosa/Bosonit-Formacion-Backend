package com.bosonit.formacion.backend.application;

import com.bosonit.formacion.backend.controller.dto.ClientInputDto;
import com.bosonit.formacion.backend.controller.dto.ClientOutputDto;

public interface ClientService {
    ClientOutputDto addClient (ClientInputDto clientInputDto);
    ClientOutputDto getClientById (String id);
    Iterable<ClientOutputDto> getAllClient (int pageNumber, int pageSize);
    ClientOutputDto updateClient (String id, ClientInputDto clientInputDto);
    void deleteClientById (String id);
}
