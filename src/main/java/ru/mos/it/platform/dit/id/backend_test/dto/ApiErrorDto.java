package ru.mos.it.platform.dit.id.backend_test.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ApiErrorDto {

    @JsonProperty("errors")
    private List<Error> errors = new ArrayList<>();

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Error {
        @JsonProperty("errorMessage")
        private String errorMessage;
    }
}
