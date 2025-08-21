package com.accounting.demo.rest.exceptionHandler;

import com.accounting.demo.exception.ApiException;
import com.accounting.demo.model.ApplicationApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.accounting.demo.enumerator.ApiExceptionEnum.ER_124;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApplicationApiException> handleJsonParseException(HttpMessageNotReadableException ex) {
        log.error("JSON parse error: ", ex);
        ApplicationApiException apiException = new ApplicationApiException();
        apiException.setMessage("Invalid request payload: " + ex.getMostSpecificCause().getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApplicationApiException> handleGeneralException(Throwable ex) {
        log.error("An unexpected error occurred: ", ex);
        ApplicationApiException apiException = new ApplicationApiException();
        apiException.setCode(ER_124.name());
        apiException.setMessage(ER_124.getDescription());
        return new ResponseEntity<>(apiException, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApplicationApiException> handleAccountNotFound(ApiException ex) {
        log.error("An API exception occurred: ", ex);
        ApplicationApiException apiException = new ApplicationApiException();
        apiException.setCode(ex.getCode());
        apiException.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.valueOf(ex.getStatusCode()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApplicationApiException> handleIllegalArgumentException(IllegalArgumentException ex) {
        log.error("An illegal argument exception occurred: ", ex);
        ApplicationApiException apiException = new ApplicationApiException();
        apiException.setCode(ER_124.name());
        apiException.setMessage(ex.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}