package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.EspecialidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EspecialidadeRepository extends JpaRepository<EspecialidadeEntity, Integer> {

    Optional<EspecialidadeEntity> findByNomeEspecialidadeIgnoreCase(String nomeEspecialidade);

    EspecialidadeEntity findByIdEspecialidade(Integer id);
}
