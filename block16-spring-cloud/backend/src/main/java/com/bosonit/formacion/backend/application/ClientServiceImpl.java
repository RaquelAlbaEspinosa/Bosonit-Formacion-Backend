package com.bosonit.formacion.backend.application;

import com.bosonit.formacion.backend.controller.dto.ClientInputDto;
import com.bosonit.formacion.backend.controller.dto.ClientOutputDto;
import com.bosonit.formacion.backend.controller.mapper.ClientMapper;
import com.bosonit.formacion.backend.domain.Client;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    MongoTemplate mongoTemplate;
    @Override
    public ClientOutputDto addClient(ClientInputDto clientInputDto) {
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        Client client = clientMapper.clientInputDtoToClient(clientInputDto);
        Client cosa = mongoTemplate.save(client);
        return clientMapper.clientToClientOutputDto(cosa);
    }

    @Override
    public ClientOutputDto getClientById(String id) {
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        return clientMapper.clientToClientOutputDto(mongoTemplate.findById(id, Client.class));
    }

    @Override
    public Iterable<ClientOutputDto> getAllClient(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Query query = new Query();
        query.with(pageRequest);
        return mongoTemplate.find(query, Client.class)
                .stream().map(client -> {
                    ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
                    return clientMapper.clientToClientOutputDto(client);
                }).toList();
    }

    @Override
    public ClientOutputDto updateClient(String id, ClientInputDto clientInputDto) {
        ClientMapper clientMapper = Mappers.getMapper(ClientMapper.class);
        Client client = clientMapper.clientInputDtoToClient(clientInputDto);
        client.setIdClient(id);
        mongoTemplate.save(client);
        return clientMapper.clientToClientOutputDto(client);
    }

    @Override
    public void deleteClientById(String id) {
        Query query = new Query(Criteria.where("idClient").is(id));
        mongoTemplate.remove(query, Client.class);
    }
}
