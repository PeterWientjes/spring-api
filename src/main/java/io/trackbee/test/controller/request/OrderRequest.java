package io.trackbee.test.controller.request;

import lombok.Data;

@Data
public class OrderRequest {
    private String id;
    private float price;
    private Long timestamp;
}
