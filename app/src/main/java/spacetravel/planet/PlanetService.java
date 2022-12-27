package spacetravel.planet;

import spacetravel.exception.NullOutputException;

public interface PlanetService {

    void createNewPlanet(Planet planet);
    void deletePlanet(Planet planet) throws NullOutputException;
}
