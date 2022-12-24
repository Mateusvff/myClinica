package com.controleClinica.controleClinicaMedica.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "MEDICO")
@IdClass(MedicoPKEntity.class)
public class MedicoEntity {

    @Id
    @Column(name = "CRM")
    private Integer crm;

    @Id
    @Column(name = "SIGLA_ESTADO")
    private String siglaEstado;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @OneToMany(mappedBy = "medico")
    @JsonBackReference
    private List<ConsultaEntity> consultas;

    @ManyToMany
    @JoinTable(name = "medico_especialidade",
               inverseJoinColumns = @JoinColumn(name = "especialidade_id"))
    private List<EspecialidadeEntity> especialidades;

    public MedicoEntity(){

    }

    public MedicoEntity(Integer crm, String siglaEstado, String nomeCompleto) {
        this.crm = crm;
        this.siglaEstado = siglaEstado;
        this.nomeCompleto = nomeCompleto;
    }

    public MedicoEntity(Integer crm, String siglaEstado, String nomeCompleto, List<EspecialidadeEntity> especialidades) {
        this.crm = crm;
        this.siglaEstado = siglaEstado;
        this.nomeCompleto = nomeCompleto;
        this.especialidades = especialidades;
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

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public List<EspecialidadeEntity> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<EspecialidadeEntity> especialidades) {
        this.especialidades = especialidades;
    }

    public List<ConsultaEntity> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaEntity> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "crm=" + crm +
                ", codeOfState=" + siglaEstado +
                ", name='" + nomeCompleto + '\'' +
                '}';
    }
}
