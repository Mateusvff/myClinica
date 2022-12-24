package com.controleClinica.controleClinicaMedica.repository;

import com.controleClinica.controleClinicaMedica.entities.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteEntity, Integer> {

    Optional<PacienteEntity> findByCpf (String cpf);

    PacienteEntity findByCpfIgnoreCase (String cpf);

}
