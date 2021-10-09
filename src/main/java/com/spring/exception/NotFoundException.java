package com.spring.exception;

/**
 * @author TuanVV
 * @since 16/09/2021
 */
public class NotFoundException extends Exception{

    private static final long serialVersionUID = -2586209354700102349L;

    public NotFoundException(){
        super();
    }

    public NotFoundException(String msg){
        super(msg);
    }

    public NotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }

}
