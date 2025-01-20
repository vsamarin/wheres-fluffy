package ru.mos.it.platform.dit.id.backend_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.mos.it.platform.dit.id.backend_test.dto.SearchPetDto;
import ru.mos.it.platform.dit.id.backend_test.entity.PetEntity;
import ru.mos.it.platform.dit.id.backend_test.exception.NotFoundException;
import ru.mos.it.platform.dit.id.backend_test.repository.PetRepository;
import ru.mos.it.platform.dit.id.backend_test.repository.specification.PetSpecification;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class PetService {

    private final PetRepository petRepository;

    public void delete(Long id) {
        petRepository.delete(
                petRepository
                        .findById(id)
                        .orElseThrow(() ->
                                new NotFoundException(format("Питомец с идентификатором %s не найден.", id))
                        )
        );
    }

    public PetEntity edit(PetEntity petEntity) {
        petRepository.findById(petEntity.id()).orElseThrow(() ->
                new NotFoundException(format("Питомец с идентификатором %s не найден.", petEntity.id()))
        );
        return petRepository.save(petEntity);
    }

    public PetEntity create(PetEntity petEntity) {
        return petRepository.save(petEntity.id(null));
    }

    public Page<PetEntity> list(SearchPetDto searchPet, Pageable pageable) {
        return petRepository.findAll(new PetSpecification(searchPet), pageable);
    }

    public PetEntity findById(Long id) {
        return petRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(format("Питомец с идентификатором %s не найден.", id)));
    }

}
