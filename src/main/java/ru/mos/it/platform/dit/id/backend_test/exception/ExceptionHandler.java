package ru.mos.it.platform.dit.id.backend_test.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import ru.mos.it.platform.dit.id.backend_test.dto.ApiErrorDto;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({Exception.class, Throwable.class})
    public ResponseEntity<?> internalServerError(Exception e) {
        return processException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> unprocessableEntity(NotFoundException e) {
        return processException(e, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({BadInputDataException.class})
    public ResponseEntity<?> unprocessableEntity(BadInputDataException e) {
        return processException(e, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({HandlerMethodValidationException.class})
    public ResponseEntity<?> validationError(HandlerMethodValidationException e) {
        return processException(e);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> unprocessableEntity(MethodArgumentNotValidException e) {
        log.error(e.getLocalizedMessage(), e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiErrorDto view = new ApiErrorDto();

        for (FieldError error : e.getFieldErrors()) {
            view.getErrors().add(new ApiErrorDto.Error(format(error.getField(), error.getDefaultMessage())));
        }
        for (ObjectError error : e.getBindingResult().getGlobalErrors()) {
            view.getErrors().add(new ApiErrorDto.Error(format(error.getObjectName(), error.getDefaultMessage())));
        }

        return new ResponseEntity<>(view, headers, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> processException(HandlerMethodValidationException e) {
        String message;
        if (Objects.nonNull(e.getDetailMessageArguments()) && ArrayUtils.isNotEmpty(e.getDetailMessageArguments())) {
            message = e.getDetailMessageArguments()[0].toString();
        } else {
            message = e.getLocalizedMessage();
        }

        log.error(message, e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiErrorDto view = new ApiErrorDto();
        view.getErrors().add(new ApiErrorDto.Error(message));
        return new ResponseEntity<>(view, headers, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<?> processException(Exception e, HttpStatus status) {
        log.error(e.getLocalizedMessage(), e);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ApiErrorDto view = new ApiErrorDto();
        view.getErrors().add(new ApiErrorDto.Error(e.getLocalizedMessage()));
        return new ResponseEntity<>(view, headers, status);
    }

    private String format(String name, String message) {
        return String.format("%s: %s", name, message);
    }

}
