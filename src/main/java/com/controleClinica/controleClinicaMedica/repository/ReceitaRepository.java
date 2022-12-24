package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.ReceitaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaEntity, Integer> {

    ReceitaEntity findByIdReceita (Integer idReceita);

    List<ReceitaEntity> findReceitaEntitiesByMedicamentosIdMedicamento(Integer idMedicamento);

    @Transactional
    @Modifying
    @Query (value = "DELETE RECEITA_MEDICAMENTO WHERE RECEITA_ID = :receita_id", nativeQuery = true)
    void deleteReceitaMedicamentoByIdReceita(@Param("receita_id") Integer receita_id);


}
