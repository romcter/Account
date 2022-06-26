package com.shop.account.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import static java.lang.String.format;
import static org.apache.logging.log4j.util.Strings.isBlank;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String CONTEXT_REQUEST_ID = "TMC_REQUEST_ID";
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = BAD_REQUEST)
    public ErrorResponse badRequestException(IllegalArgumentException ex, WebRequest request) {
        return buildResponse(BAD_REQUEST, ex, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = NOT_FOUND)
    public ErrorResponse notFoundException(ResourceNotFoundException ex, WebRequest request) {
        return buildResponse(NOT_FOUND, ex, request);
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(value = FORBIDDEN)
    public ErrorResponse forbiddenException(SecurityException ex, WebRequest request) {
        return buildResponse(FORBIDDEN, ex, request);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = INTERNAL_SERVER_ERROR)
    public ErrorResponse globalException(Exception ex, WebRequest request) {
        return buildResponse(INTERNAL_SERVER_ERROR, ex, request);
    }

    private ErrorResponse buildResponse(HttpStatus status, Exception ex, WebRequest request) {
        boolean useDefaultMessage = isBlank(ex.getMessage()) || INTERNAL_SERVER_ERROR == status;
        String message = useDefaultMessage ? status.getReasonPhrase() : ex.getMessage();
        String requestId = (String) request.getAttribute(CONTEXT_REQUEST_ID, SCOPE_REQUEST);

        LOGGER.error(format("REQUEST ID=[%s] TYPE=[%s]", requestId, status), ex);

        return ErrorResponse.builder().status(status.value()).message(message).requestId(requestId).build();
    }
}
