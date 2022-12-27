package spacetravel.client;

import org.hibernate.Session;
import org.hibernate.Transaction;
import spacetravel.exception.NullOutputException;
import spacetravel.hibernate_util.HibernateUtil;

public class ClientCrudService implements ClientService {

    HibernateUtil util = HibernateUtil.getInstance();
    @Override
    public void createNewClient(Client client) {
        if (client.getName().length() <= 3) {
            System.out.println("A new client cannot be created due to the restrictions," +
                    " the name must have at least 2 characters.");
        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                Client newClient = new Client();
                newClient.setName(client.getName());
                session.persist(newClient);
                transaction.commit();
                System.out.println("The client with name " + client.getName() + " has been created.\n" + newClient);
            }
        }
    }

    @Override
    public void deleteClient(Client client) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Client existingClient = session.get(Client.class, client.getId());
            if (client == null) {
                throw new NullOutputException("The client with name " + client.getName() + " does not exists.");
            } else {
                session.remove(existingClient);
                transaction.commit();
                System.out.println("The client with name " + client.getName() + " was deleted.");
            }
        }
    }
}
