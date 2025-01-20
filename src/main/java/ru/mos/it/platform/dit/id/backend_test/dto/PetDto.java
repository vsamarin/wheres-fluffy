package ru.mos.it.platform.dit.id.backend_test.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.mos.it.platform.dit.id.backend_test.entity.PetStatus;
import ru.mos.it.platform.dit.id.backend_test.validator.PetStatusConstraint;
import ru.mos.it.platform.dit.id.backend_test.validator.PetUniqueNameConstraint;


@Getter
@Setter
@Accessors(chain = true)
@PetUniqueNameConstraint
public class PetDto {

    @Parameter(description = "Идентификатор", example = "1")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Positive
    private Long id;

    @Parameter(description = "Название", example = "Императрица")
    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Parameter(description = "Порода", example = "Кошка, Шотландская вислоухая")
    @NotBlank
    @Size(min = 1, max = 255)
    private String species;

    @Parameter(description = "Описание",
            example = "Кошка была потеряна в городе Москва, в районе Северное Тушино, ул. Свободы 67к3, во дворе, " +
                    "отличительные черты: красный ошейник с позолоченным кулоном")
    @NotBlank
    @Size(min = 1, max = 255)
    private String description;

    @Parameter(description = "Статус", example = "MISSING")
    @PetStatusConstraint(anyOf = {PetStatus.FOUND, PetStatus.MISSING, PetStatus.NOT_FOUND})
    private PetStatus status;

}
