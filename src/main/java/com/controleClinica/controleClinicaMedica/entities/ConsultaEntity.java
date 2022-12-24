package com.controleClinica.controleClinicaMedica.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CONSULTA")
public class ConsultaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idConsulta;

    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "crm", referencedColumnName = "CRM"),
            @JoinColumn(name = "siglaEstado", referencedColumnName = "SIGLA_ESTADO")})
    @JsonManagedReference
    private MedicoEntity medico;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonManagedReference
    private PacienteEntity paciente;

    @OneToMany (cascade = CascadeType.REMOVE)
    @JoinColumn(name = "consulta_id")
    @JsonManagedReference
        private List<ReceitaEntity> receitas = new ArrayList<>();

    public ConsultaEntity() {
    }

    public ConsultaEntity(MedicoEntity medico, PacienteEntity paciente) {
        this.medico = medico;
        this.paciente = paciente;
    }

    public Integer getIdConsulta() {
        return idConsulta;
    }

    public List<ReceitaEntity> getReceitas() {
        return receitas;
    }

    public void setReceitas(List<ReceitaEntity> receitas) {
        this.receitas = receitas;
    }

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public PacienteEntity getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteEntity paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "idConsulta=" + idConsulta +
                '}';
    }
}
