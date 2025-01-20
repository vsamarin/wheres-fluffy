package ru.mos.it.platform.dit.id.backend_test.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
public class HealthCheckDto {

    @Schema(description = "Статус")
    private HealthCheckStatus status;

}
