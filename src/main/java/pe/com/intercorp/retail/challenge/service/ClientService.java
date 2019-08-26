package pe.com.intercorp.retail.challenge.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.com.intercorp.retail.challenge.api.dto.Client;
import pe.com.intercorp.retail.challenge.api.dto.ClientListResults;
import pe.com.intercorp.retail.challenge.data.ClientRepository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ClientService {
    private ClientRepository clientRepository;

    public CompletableFuture<Client> createOrUpdate(Client client) {
        if (client.getId() == null) client.setId(UUID.randomUUID().toString());
        return clientRepository.save(client);
    }

    public CompletableFuture<ClientListResults> list(Integer startAt, Integer size) {
        return clientRepository.list(startAt, size).thenApply(clients -> new ClientListResults(clients));
    }

}
