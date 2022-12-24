package com.controleClinica.controleClinicaMedica.dto;

import java.util.Date;
import java.util.List;

public class ReceitaDTO {

    private Date dataEmissao;

    private String descricao;

    private Integer idConsulta;

    private List<Integer> idMedicamentos;

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }

    public List<Integer> getIdMedicamentos() {
        return idMedicamentos;
    }

    public void setIdMedicamentos(List<Integer> idMedicamentos) {
        this.idMedicamentos = idMedicamentos;
    }
}
