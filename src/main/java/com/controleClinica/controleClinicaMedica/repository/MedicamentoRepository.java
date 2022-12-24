package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.MedicamentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicamentoRepository extends JpaRepository<MedicamentoEntity, Integer> {

    Optional<MedicamentoEntity> findByNomeTecnicoIgnoreCase(String nomeTecnico);

    MedicamentoEntity findByIdMedicamento(Integer idMedicamento);

}
