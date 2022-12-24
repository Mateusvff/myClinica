package com.controleClinica.controleClinicaMedica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PACIENTE")
public class PacienteEntity {

    @Id
    @Column(name = "CPF")
    private String cpf;

    @Column(name = "SEXO")
    private String sexo;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

    @Column(name = "TELEFONE")
    private String telefone;

    @OneToMany(mappedBy = "paciente")
    @JsonBackReference
    private List<ConsultaEntity> consultas;

    public PacienteEntity(){

    }

    public PacienteEntity(String cpf, String sexo, String nomeCompleto, String telefone){
        this.cpf = cpf;
        this.sexo = sexo;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
    }

    public PacienteEntity(String cpf, String sexo, String nomeCompleto, String telefone, List<ConsultaEntity> consultas){
        this.cpf = cpf;
        this.sexo = sexo;
        this.nomeCompleto = nomeCompleto;
        this.telefone = telefone;
        this.consultas = consultas;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<ConsultaEntity> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<ConsultaEntity> consultas) {
        this.consultas = consultas;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "cpf='" + cpf + '\'' +
                ", sexo=" + sexo +
                ", nomeCompleto='" + nomeCompleto + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
