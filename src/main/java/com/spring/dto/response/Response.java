package com.spring.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Tuan
 * @since 19/09/2021
 *
 * @param <T>
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private T data;
    private Object errors;

     /**
     * @author Tuan
     * @since 19/09/2021
     *
     * @param msgError
     */
    public void addErrorMsgToResponse(String msgError) {
        ResponseError error = new ResponseError()
                .setDetails(msgError)
                .setTimestamp(LocalDateTime.now());
        setErrors(error);
    }
}