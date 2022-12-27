package spacetravel.planet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import spacetravel.exception.NullOutputException;
import spacetravel.hibernate_util.HibernateUtil;

public class PlanetCrudService implements PlanetService{

    HibernateUtil util = HibernateUtil.getInstance();

    @Override
    public void createNewPlanet(Planet planet) {
        if (!planet.getId().matches("[A-Z0-9]*$")) {
            System.out.println("The planet ID must contain capital letters without special characters.");
        } else if (planet.getName().length() < 1) {
            System.out.println("The name of the planet must contain at least one character.");

        } else {
            try (Session session = util.getSessionFactory().openSession()) {
                Transaction transaction = session.beginTransaction();
                planet = session.get(Planet.class, planet.getId());
                if (planet != null) {
                    System.out.println("The planet with id " + planet.getId() + " already exists.");
                } else {
                    Planet newPlanet = new Planet();
                    newPlanet.setId(planet.getId());
                    newPlanet.setName(planet.getName());
                    session.persist(newPlanet);
                    System.out.println("New planet " + planet.getName() + " has been created.\n" + newPlanet);
                    transaction.commit();
                }
            }
        }
    }

    @Override
    public void deletePlanet(Planet planet) throws NullOutputException {
        try (Session session = util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            planet = session.get(Planet.class, planet.getId());
            if (planet == null) {
                throw new NullOutputException("The planet with id " + planet.getId() + " wasn't found.");
            } else {
                session.remove(planet);
                transaction.commit();
                System.out.println("The planet with id " + planet.getId() + " was deleted");
            }
        }
    }
    }

