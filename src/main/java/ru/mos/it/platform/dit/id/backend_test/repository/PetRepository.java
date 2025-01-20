package ru.mos.it.platform.dit.id.backend_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.mos.it.platform.dit.id.backend_test.entity.PetEntity;

import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity, Long>, JpaSpecificationExecutor<PetEntity> {
    Optional<PetEntity> findOneByIdNotAndName(Long id, String code);
    Optional<PetEntity> findOneByName(String code);
}
