package com.controleClinica.controleClinicaMedica.exceptions;

public class ObjectMissingRequirementsException extends RuntimeException{

    public ObjectMissingRequirementsException(String mensagem){
        super(mensagem);
    }

    public ObjectMissingRequirementsException(String mensagem, Throwable cause){
        super(mensagem, cause);
    }

}
