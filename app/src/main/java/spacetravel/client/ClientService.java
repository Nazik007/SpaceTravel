package spacetravel.client;

import spacetravel.exception.NullOutputException;

public interface ClientService {

    void createNewClient(Client client);
    void deleteClient(Client client) throws NullOutputException;
}
