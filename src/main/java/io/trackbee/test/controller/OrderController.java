package io.trackbee.test.controller;

import io.trackbee.test.controller.request.OrderRequest;
import io.trackbee.test.domain.Store;
import io.trackbee.test.service.OrderService;
import io.trackbee.test.service.StoreService;
import io.trackbee.test.service.TrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final StoreService storeService;
    private final TrackingService trackingService;

    /**
     * Saves given Order for given Store
     * Will return 404 (NOT_FOUND) if store doesn't exist
     * Will send tracking data to all available platforms
     *
     */
    @PostMapping("/{storeId}")
    public ResponseEntity<Object> postTracking(@PathVariable("storeId") Long storeId, @RequestBody OrderRequest orderRequest) {

        Optional<Store> store = this.storeService.retrieveStore(storeId);
        if (store.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Could not find Store");
        }

        orderService.placeOrder(store.get(), orderRequest);
        trackingService.track(store.get(), orderRequest);

        return ResponseEntity.ok().body("success");
    }
}
