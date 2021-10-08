package com.spring.exception;

/**
 * @author TuanVV
 * @since 16/09/2021
 */
public class InvalidUpdateException extends Exception{

    private static final long serialVersionUID = -6443362632495638948L;

    public InvalidUpdateException(){
        super();
    }

    public InvalidUpdateException(String msg){
        super(msg);
    }

    public InvalidUpdateException(String msg, Throwable cause){
        super(msg, cause);
    }
}
