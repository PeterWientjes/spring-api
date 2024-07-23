package io.trackbee.test.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private String apiKey;

    @OneToMany(mappedBy = "store")
    private List<StorePlatformConnection> storePlatformConnectionList;

    @OneToMany(mappedBy = "store")
    private List<Order> orderList;

}
