package com.controleClinica.controleClinicaMedica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "RECEITA")
public class ReceitaEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idReceita;

    @Column(name = "DATA_EMISSAO")
    private Date dataEmissao;

    @Column(name = "DESCRICAO")
    private String descricaoReceita;

    @ManyToOne
    @JoinColumn(name = "consulta_id")
    @JsonBackReference
    private ConsultaEntity consulta;

    @ManyToMany
    @JoinTable( name = "receita_medicamento",
                joinColumns = @JoinColumn(name = "receita_id"),
                inverseJoinColumns = @JoinColumn(name = "medicamento_id"))
    private List<MedicamentoEntity> medicamentos = new ArrayList<>();

    public ReceitaEntity() {
    }

    public ReceitaEntity(Date dataEmissao, String descricaoReceita) {
        this.dataEmissao = dataEmissao;
        this.descricaoReceita = descricaoReceita;
    }

    public ReceitaEntity(Date dataEmissao, String descricaoReceita, List<MedicamentoEntity> medicamentos, ConsultaEntity consulta) {
        this.dataEmissao = dataEmissao;
        this.descricaoReceita = descricaoReceita;
        this.medicamentos = medicamentos;
        this.consulta = consulta;
    }

    public Integer getIdReceita() {
        return idReceita;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public String getDescricaoReceita() {
        return descricaoReceita;
    }

    public void setDescricaoReceita(String descricaoReceita) {
        this.descricaoReceita = descricaoReceita;
    }

    public ConsultaEntity getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaEntity consulta) {
        this.consulta = consulta;
    }

    public List<MedicamentoEntity> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<MedicamentoEntity> medicamentos) {
        this.medicamentos = medicamentos;
    }

    @Override
    public String toString() {
        return "Receita{" +
                "idReceita=" + idReceita +
                ", dataEmissao=" + dataEmissao +
                ", descricaoReceita='" + descricaoReceita + '\'' +
                '}';
    }
}
