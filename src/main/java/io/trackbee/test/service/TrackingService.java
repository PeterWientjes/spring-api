package io.trackbee.test.service;

import io.trackbee.test.controller.request.OrderRequest;
import io.trackbee.test.domain.ConnectionCredentials;
import io.trackbee.test.domain.PlatformType;
import io.trackbee.test.domain.Store;
import io.trackbee.test.domain.StorePlatformConnection;
import io.trackbee.test.repository.ConnectionCredentialsRepository;
import io.trackbee.test.repository.StorePlatformConnectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class TrackingService {
    private final StorePlatformConnectionRepository storePlatformConnectionRepository;
    private final ConnectionCredentialsRepository connectionCredentialsRepository;

    /**
     * Sends Tracking data for given Store to all the available {@link PlatformType}
     *
     */
    public void track(Store store, OrderRequest orderRequest) {
        Arrays.stream(PlatformType.values()).forEach((platformType) -> {
            StorePlatformConnection storePlatformConnection = new StorePlatformConnection();
            storePlatformConnection.setStore(store);
            storePlatformConnection.setPlatformType(platformType);

            storePlatformConnectionRepository.save(storePlatformConnection);

            ConnectionCredentials connectionCredentials = new ConnectionCredentials();
            connectionCredentials.setPixelId("some value");
            connectionCredentials.setPixelAccessToken("some value");
            connectionCredentials.setStorePlatformConnection(storePlatformConnection);

            connectionCredentialsRepository.save(connectionCredentials);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
