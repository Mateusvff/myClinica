package com.controleClinica.controleClinicaMedica.exceptions;

public class ObjectNotAllowedToDeleteException extends RuntimeException{


    public ObjectNotAllowedToDeleteException(String mensagem){
        super(mensagem);
    }

    public ObjectNotAllowedToDeleteException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
