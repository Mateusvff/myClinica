package com.controleClinica.controleClinicaMedica.entities;

import java.io.Serializable;

public class MedicoPKEntity implements Serializable {

    private Integer crm;
    private String siglaEstado;

    public MedicoPKEntity() {
    }

    public Integer getCrm() {
        return crm;
    }

    public void setCrm(Integer crm) {
        this.crm = crm;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}
