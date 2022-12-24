package com.controleClinica.controleClinicaMedica.dto;

import java.io.Serializable;
import java.util.List;

public class MedicoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer crm;

    private String siglaEstado;

    private String nomeCompleto;

    private List<Integer> idEspecialidades;

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public List<Integer> getIdEspecialidades() {
        return idEspecialidades;
    }

    public void setIdEspecialidades(List<Integer> idEspecialidades) {
        this.idEspecialidades = idEspecialidades;
    }
}
