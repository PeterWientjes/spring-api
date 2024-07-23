package io.trackbee.test.repository;

import io.trackbee.test.domain.ConnectionCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionCredentialsRepository extends JpaRepository<ConnectionCredentials, Long> {
}
