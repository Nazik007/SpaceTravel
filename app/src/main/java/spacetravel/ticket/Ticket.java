package spacetravel.ticket;

import jakarta.persistence.*;
import lombok.Data;
import spacetravel.client.Client;
import spacetravel.planet.Planet;


@Data
@Entity
public class Ticket {
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @Column (name = "created_at")
    private String createdAt;

    @ManyToOne (cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;

    @OneToOne()
    @JoinColumn(name = "from_planet_id", referencedColumnName = "id")
    private Planet planetFrom;

    @OneToOne
    @JoinColumn(name = "to_planet_id", referencedColumnName = "id")
    private Planet planetTo;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt='" + createdAt + '\'' +
                ", client=" + client +
                ", planetFrom=" + planetFrom +
                ", planetTo=" + planetTo +
                '}';
    }
}
