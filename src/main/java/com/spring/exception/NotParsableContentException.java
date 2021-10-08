package com.spring.exception;

/**
 * @author TuanVV
 * @since 16/09/2021
 */
public class NotParsableContentException extends  Exception{

    private static final long serialVersionUID = 6208890125157318839L;

    public NotParsableContentException(String msg){
        super(msg);
    }

    public NotParsableContentException(String msg, Throwable cause){
        super(msg, cause);
    }
}
