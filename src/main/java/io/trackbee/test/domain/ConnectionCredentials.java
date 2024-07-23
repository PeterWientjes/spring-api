package io.trackbee.test.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ConnectionCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String pixelId;

    @Column
    private String pixelAccessToken;

    @ManyToOne
    @JoinColumn(name = "storePlatformConnection", nullable = false)
    private StorePlatformConnection storePlatformConnection;
}
