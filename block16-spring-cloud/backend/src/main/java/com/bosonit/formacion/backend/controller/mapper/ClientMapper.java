package com.bosonit.formacion.backend.controller.mapper;

import com.bosonit.formacion.backend.controller.dto.ClientInputDto;
import com.bosonit.formacion.backend.controller.dto.ClientOutputDto;
import com.bosonit.formacion.backend.domain.Client;
import org.mapstruct.Mapper;

@Mapper
public interface ClientMapper {
    Client clientInputDtoToClient (ClientInputDto clientInputDto);
    ClientOutputDto clientToClientOutputDto (Client client);
}
