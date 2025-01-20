package ru.mos.it.platform.dit.id.backend_test.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadInputDataException extends RuntimeException {
}
