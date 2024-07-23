package io.trackbee.test.controller;

import io.trackbee.test.controller.request.StoreRequest;
import io.trackbee.test.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("store")
public class StoreController {
    private final StoreService storeService;

    /**
     * Creates a store based on the give name, will return random generated api key for that store to use for further
     * request
     *
     * @param store {@link StoreRequest}
     * @return {@link String} with the api key to use for request for this store
     */
    @PostMapping("/create")
    public ResponseEntity<String> createStore(@RequestBody StoreRequest store) {
        String storeApiKey = this.storeService.createStore(store.getName());
        return ResponseEntity.ok().body(storeApiKey);
    }
}
