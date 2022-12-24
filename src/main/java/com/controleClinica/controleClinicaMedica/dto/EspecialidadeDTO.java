package com.controleClinica.controleClinicaMedica.dto;

import java.io.Serializable;

public class EspecialidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nomeEspecialidade;

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }
}
