package io.trackbee.test.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class StorePlatformConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private PlatformType platformType;

    @ManyToOne
    @JoinColumn(name = "store", nullable = true)
    private Store store;

    @OneToMany(mappedBy = "storePlatformConnection")
    private List<ConnectionCredentials> connectionCredentialsList;
}
