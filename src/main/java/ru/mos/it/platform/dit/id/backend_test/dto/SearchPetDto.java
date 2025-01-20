package ru.mos.it.platform.dit.id.backend_test.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchPetDto {

    @Parameter(description = "Название", example = "Майло")
    private String name;

    @Parameter(description = "Порода", example = "Джек-Рассел-Терьер")
    private String species;

}
