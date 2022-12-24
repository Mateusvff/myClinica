package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoEntity, Integer> {

    Optional<MedicoEntity> findByCrmAndSiglaEstado(Integer crm, String siglaEstado);

    MedicoEntity findByCrmAndSiglaEstadoIgnoreCase(Integer crm, String siglaEstado);

    List<MedicoEntity> findMedicoEntitiesByEspecialidadesIdEspecialidade(Integer idEspecialidade);

}