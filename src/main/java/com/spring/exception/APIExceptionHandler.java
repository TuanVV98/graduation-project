package com.spring.exception;

import com.spring.dto.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ServerErrorException;

import com.fasterxml.jackson.core.JsonParseException;

/**
 * Class that implements a handler of exceptions and errors in the API, using {@ControllerAdvice}
 * and sending the proper response to the client.
 *
 * @param <T>
 * @author TuanVV
 * @since 16/09/2021
 */

@RestControllerAdvice
public class APIExceptionHandler<T> {

    /**
     * status code = 403
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     * @author TuanVV
     * @since 16/09/2021
     */
    @ExceptionHandler(value = {InvalidUpdateException.class})
    protected ResponseEntity<Response<T>> handleInvalidUpdateException(InvalidUpdateException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    /**
     * status code = 404
     * @author TuanVV
     * @since 16/09/2021
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     */
    @ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Response<T>> handleNotFoundException(NotFoundException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * status code = 409
     *
     * @author TuanVV
     * @since 16/09/2021
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     */
    @ExceptionHandler(value = {HttpClientErrorException.Conflict.class})
    protected ResponseEntity<Response<T>> handleConflictException(HttpClientErrorException exception) {
        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * status code = 422
     *
     * @author TuanVV
     * @since 16/09/2021
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     */
    @ExceptionHandler(value = {HttpMessageNotReadableException.class, JsonParseException.class, NotParsableContentException.class})
    protected ResponseEntity<Response<T>> handleMessageNotReadableException(Exception exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    /**
     * status code = 429
     *
     * @author TuanVV
     * @since 16/09/2021
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     */
    @ExceptionHandler(value = { HttpClientErrorException.TooManyRequests.class })
    protected ResponseEntity<Response<T>> handleTooManyRequestException(HttpClientErrorException exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(response);
    }

    /**
     * status code = 500
     *
     * @author TuanVV
     * @since 16/09/2021
     *
     * @param exception
     * @return ResponseEntity<Response < T>>
     */
    @ExceptionHandler(value = { ServerErrorException.class })
    protected ResponseEntity<Response<T>> handleAPIException(ServerErrorException exception) {

        Response<T> response = new Response<>();
        response.addErrorMsgToResponse(exception.getLocalizedMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
