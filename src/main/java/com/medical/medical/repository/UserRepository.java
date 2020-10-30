package com.medical.medical.repository;

import com.medical.medical.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByClientId(Long id);

    UserEntity findByClientIdAndRole(Long patientId, String patient);
}
