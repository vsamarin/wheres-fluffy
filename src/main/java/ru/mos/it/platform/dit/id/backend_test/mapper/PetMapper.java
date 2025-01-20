package ru.mos.it.platform.dit.id.backend_test.mapper;

import org.springframework.stereotype.Service;
import ru.mos.it.platform.dit.id.backend_test.dto.PetDto;
import ru.mos.it.platform.dit.id.backend_test.entity.PetEntity;

@Service
public class PetMapper implements Mapper<PetEntity, PetDto> {

    @Override
    public PetDto map(PetEntity entity) {
        return entity == null ?
                null :
                new PetDto()
                        .setId(entity.id())
                        .setName(entity.name())
                        .setSpecies(entity.species())
                        .setDescription(entity.description())
                        .setStatus(entity.status());
    }

    public PetEntity map(PetDto dto) {
        return dto == null ?
                null :
                new PetEntity()
                        .id(dto.getId())
                        .name(dto.getName())
                        .species(dto.getSpecies())
                        .description(dto.getDescription())
                        .status(dto.getStatus());
    }

    public PetEntity map(Long id, PetDto dto) {
        return dto == null ?
                null :
                new PetEntity()
                        .id(id)
                        .name(dto.getName())
                        .species(dto.getSpecies())
                        .description(dto.getDescription())
                        .status(dto.getStatus());
    }

}
