package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsultaRepository extends JpaRepository<ConsultaEntity, Integer> {

    ConsultaEntity findByIdConsulta (Integer idConsulta);

    Optional<ConsultaEntity> findConsultaEntityByIdConsulta(Integer id);
}
