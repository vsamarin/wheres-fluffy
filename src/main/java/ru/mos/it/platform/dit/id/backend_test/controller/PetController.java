package ru.mos.it.platform.dit.id.backend_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mos.it.platform.dit.id.backend_test.controller.examples.PetExamples;
import ru.mos.it.platform.dit.id.backend_test.dto.*;
import ru.mos.it.platform.dit.id.backend_test.dto.SearchPetDto;
import ru.mos.it.platform.dit.id.backend_test.exception.NotFoundException;
import ru.mos.it.platform.dit.id.backend_test.mapper.PetMapper;
import ru.mos.it.platform.dit.id.backend_test.service.PetService;


@RestController
@RequestMapping("/api/v1/pet")
@RequiredArgsConstructor
@Tag(name = "Pet", description = "CRUD операции над записями \"Питомец\"")
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "500",
                description = "Ошибка сервиса",
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ApiErrorDto.class),
                        examples = @ExampleObject(value = PetExamples.list500)
                )
        )
})
public class PetController {

    private final PetService petService;
    private final PetMapper petMapper;

    @Operation(
            summary = "Получение питомца по идентификатору",
            operationId = "pet_find"
    )
    @GetMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Питомец",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDto.class),
                            examples = @ExampleObject(value = PetExamples.findById200)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Питомец не найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorDto.class),
                            examples = @ExampleObject(value = PetExamples.findById404)
                    )
            )
    })
    public PetDto find(
            @Parameter(description = "Идентификатор", example = "1")
            @PathVariable("id") Long id
    ) {
        return petMapper.map(petService.findById(id));
    }

    @Operation(
            summary = "Получение списка питомцев по фильтру",
            operationId = "pet_list"
    )
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Список объектов",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetListDto.class),
                            examples = @ExampleObject(value = PetExamples.list200)
                    )
            )
    })
    @ResponseStatus(HttpStatus.OK)
    public ListDto<PetDto> list(
            @ParameterObject SearchPetDto searchPet,
            @PageableDefault(
                    sort = "id",
                    direction = Sort.Direction.ASC
            )
            @ParameterObject Pageable pageable
    ) {
        return petMapper.map(petService.list(searchPet, pageable).map(petMapper::map));
    }

    @Operation(
            summary = "Создание новой записи о питомце",
            operationId = "pet_create"
    )
    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Созданная запись о питомце",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDto.class),
                            examples = @ExampleObject(value = PetExamples.findById200)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации входных данных записи о питомце",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorDto.class),
                            examples = @ExampleObject(value = PetExamples.createOrEdit400)
                    )
            )
    })
    public PetDto create(
            @Valid
            @Schema(example = PetExamples.create200)
            @RequestBody PetDto petDto
    ) {
        return petMapper.map(petService.create(petMapper.map(petDto)));
    }

    @Operation(
            summary = "Редактирование существующей записи о питомце",
            operationId = "pet_edit"
    )
    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Обновленная запись о питомце",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PetDto.class),
                            examples = @ExampleObject(value = PetExamples.findById200)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Ошибка валидации входных данных о питомце",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorDto.class),
                            examples = @ExampleObject(value = PetExamples.createOrEdit400)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Питомец не найден",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ApiErrorDto.class),
                            examples = @ExampleObject(value = PetExamples.findById404)
                    )
            )
    })
    public PetDto edit(
            @Parameter(description = "Идентификатор питомца", example = "1")
            @PathVariable("id") Long id,
            @Valid
            @Schema(example = PetExamples.create200)
            @RequestBody PetDto petDto
    ) {
        return petMapper.map(petService.edit(petMapper.map(id, petDto)));
    }

    @Operation(
            summary = "Удаление записи о питомце",
            operationId = "pet_delete"
    )
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Питомец не найден"
            )
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Идентификатор питомца", example = "1")
            @PathVariable("id") Long id
    ) {
        try {
            petService.delete(id);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

}
