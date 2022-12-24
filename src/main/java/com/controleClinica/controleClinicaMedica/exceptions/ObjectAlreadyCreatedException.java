package com.controleClinica.controleClinicaMedica.exceptions;

public class ObjectAlreadyCreatedException extends RuntimeException{

    public ObjectAlreadyCreatedException(String mensagem){
        super(mensagem);
    }

    public ObjectAlreadyCreatedException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }
}
