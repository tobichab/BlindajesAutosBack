package com.example.Blindajes.converters;

import com.example.Blindajes.dto.ClientDTO;
import com.example.Blindajes.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ClientDTOToClient implements Converter<ClientDTO, Client> {

    @Override
    public Client convert(ClientDTO source) {
        Client client = new Client();
        client.setId(source.getId());
        client.setName(source.getName());
        return client;
    }
}
