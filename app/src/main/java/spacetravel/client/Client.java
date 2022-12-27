package spacetravel.client;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Client {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    @Column
    private String name;
}
