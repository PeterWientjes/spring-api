package io.trackbee.test.service;

import io.trackbee.test.domain.Store;
import io.trackbee.test.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    /**
     * Creates store in database with given name, will generate random API key for that store
     *
     * @param storeName {@link String} name of the store to be created
     * @return {@link String} Generated API key
     */
    public String createStore(String storeName) {
        Store store = new Store();
        store.setName(storeName);

        String uuid = UUID.randomUUID().toString();
        store.setApiKey(uuid);

        this.storeRepository.save(store);

        return uuid;
    }

    /**
     * Returns store of given ID if in database
     *
     * @param storeId {@link Long} id of store to be found
     * @return {@link Optional<Store>} Filled with {@link Store} if found else empty
     */
    public Optional<Store> retrieveStore(Long storeId) {
        return this.storeRepository.findById(storeId);
    }
}
