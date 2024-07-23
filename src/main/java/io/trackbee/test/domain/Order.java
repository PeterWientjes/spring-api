package io.trackbee.test.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Order {
    @Id
    private String id;

    @Column
    private Float price;

    @Column
    private Long timestamp;

    @ManyToOne
    @JoinColumn(name = "store")
    private Store store;
}
