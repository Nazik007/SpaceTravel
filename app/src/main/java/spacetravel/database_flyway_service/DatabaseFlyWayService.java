package spacetravel.database_flyway_service;

import org.flywaydb.core.Flyway;
import spacetravel.preferences.PropertiesFileReader;

import java.io.IOException;

public class DatabaseFlyWayService {

    public void initFlyWayDB () throws IOException {
        String url = new PropertiesFileReader().getPath();

        Flyway flyway = Flyway.configure()
                .dataSource(url, null, null)
                .load();
        flyway.migrate();
    }
}
