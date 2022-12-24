package com.controleClinica.controleClinicaMedica.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ESPECIALIDADE")
public class EspecialidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidade;

    @Column (name = "Especialidade")
    private String nomeEspecialidade;

    @ManyToMany(mappedBy = "especialidades")
    private List<MedicoEntity> medicos = new ArrayList<>();

    public EspecialidadeEntity() {
    }

    public EspecialidadeEntity(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }

    @Override
    public String toString() {
        return "Especialidade{" +
                "idEspecialidade=" + idEspecialidade +
                ", nomeEspecialidade='" + nomeEspecialidade + '\'' +
                '}';
    }
}
