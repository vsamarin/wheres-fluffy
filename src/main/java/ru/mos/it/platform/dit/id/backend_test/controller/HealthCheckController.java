package ru.mos.it.platform.dit.id.backend_test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.mos.it.platform.dit.id.backend_test.controller.examples.HealthCheckExamples;
import ru.mos.it.platform.dit.id.backend_test.dto.HealthCheckDto;
import ru.mos.it.platform.dit.id.backend_test.dto.HealthCheckStatus;
import ru.mos.it.platform.dit.id.backend_test.repository.PetRepository;

@RestController
@RequiredArgsConstructor
@Tag(name = "HealthCheck", description = "Доступность сервиса")
public class HealthCheckController {

    private final PetRepository petRepository;

    @Operation(
            summary = "Получение информации о доступности сервиса",
            operationId = "healthcheck"
    )
    @GetMapping(
            path = "/health/",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Сервис доступен",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HealthCheckDto.class),
                            examples = @ExampleObject(value = HealthCheckExamples.ok200)
                    )
            ),

            @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервиса во внутренней работе сервиса",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = HealthCheckDto.class),
                            examples = @ExampleObject(value = HealthCheckExamples.internalError500)
                    )
            )
    })
    public HealthCheckDto health() {
        try {
            petRepository.findById(1L);
            return new HealthCheckDto(HealthCheckStatus.OK);
        } catch (Exception e) {
            return new HealthCheckDto(HealthCheckStatus.INTERNAL_ERROR);
        }
    }

}
