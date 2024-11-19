package com.elm.challenge.carshowroom.web.exception;

import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({
            BadRequestException.class,
            IllegalArgumentException.class,
            IllegalStateException.class,
            IllegalAccessException.class,
            ServerWebInputException.class
    })
    public ProblemDetail handleBadRequestExceptions(Exception e) {
        logger.error("BadRequestException: " + e.getMessage(), e);
        return createProblemDetail(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationExceptions(MethodArgumentNotValidException e) {
        List<String> fieldErrorMessages = e.getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();

        String objectErrorDetails = e.getAllErrors().stream()
                .filter(error -> !fieldErrorMessages.contains(error.getDefaultMessage()))
                .map(error -> error.getObjectName() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        String fieldErrorDetails = e.getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        String errorDetails = objectErrorDetails + ", " + fieldErrorDetails;
        logger.error("Validation error: " + errorDetails, e);
        return createProblemDetail(HttpStatus.BAD_REQUEST, errorDetails);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException e) {
        logger.error("Entity not found: " + e.getMessage(), e);
        return createProblemDetail(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ProblemDetail handleAuthDeniedException(AuthorizationDeniedException e) {
        logger.error("Access denied: " + e.getMessage(), e);
        return createProblemDetail(HttpStatus.FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception e) {
        logger.error("Unhandled exception: " + e.getMessage(), e);
        return createProblemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    }

    private ProblemDetail createProblemDetail(HttpStatus status, String detail) {
        if (detail != null) {
            return ProblemDetail.forStatusAndDetail(status, detail);
        } else {
            return ProblemDetail.forStatus(status);
        }
    }
}
