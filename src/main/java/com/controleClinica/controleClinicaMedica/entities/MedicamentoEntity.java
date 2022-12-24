package com.controleClinica.controleClinicaMedica.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEDICAMENTO")
public class MedicamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicamento;

    @Column(name = "NOME_TÉCNICO")
    private String nomeTecnico;

    @Column(name = "DOSAGEM")
    private String dosagem;

    @Column(name = "LABORATÓRIO")
    private String laboratorio;

    @ManyToMany (mappedBy = "medicamentos")
    private List<ReceitaEntity> receitas = new ArrayList<>();

    public MedicamentoEntity() {
    }

    public MedicamentoEntity(String nomeTecnico, String dosagem, String laboratorio) {
        this.nomeTecnico = nomeTecnico;
        this.dosagem = dosagem;
        this.laboratorio = laboratorio;
    }

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "idMedicamento=" + idMedicamento +
                ", nomeTecnico='" + nomeTecnico + '\'' +
                ", dosagem='" + dosagem + '\'' +
                ", laboratorio='" + laboratorio + '\'' +
                '}';
    }
}
