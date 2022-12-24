package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.PacienteDTO;
import com.controleClinica.controleClinicaMedica.entities.PacienteEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectAlreadyCreatedException;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectMissingRequirementsException;
import com.controleClinica.controleClinicaMedica.repository.PacienteRepository;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.*;

@Service
public class PacienteService {

    private static final Logger LOGGER = LogManager.getLogger(PacienteService.class);

    @Autowired
    private PacienteRepository pacienteRepository;

    public void createPaciente(PacienteDTO pacienteDTO){

        PacienteEntity pacienteEntity = new PacienteEntity();

        pacienteEntity.setCpf(pacienteDTO.getCpf());
        pacienteEntity.setNomeCompleto(pacienteDTO.getNome());
        pacienteEntity.setSexo(pacienteDTO.getSexo().toUpperCase());
        pacienteEntity.setTelefone(pacienteDTO.getTelefone());

        if(pacienteRepository.findByCpf(pacienteDTO.getCpf()).isPresent()){
            throw new ObjectAlreadyCreatedException(DEFAULT_PACIENTE_EXCEPTION_MESSAGE + pacienteDTO.getCpf());
        } else {
            LOGGER.info("creating paciente: cpf = {}", pacienteEntity.getCpf());
            pacienteRepository.save(pacienteEntity);
        }
    }

    public PacienteEntity findPacienteByCpf(String cpf){
        LOGGER.info("getting paciente: cpf = {}", cpf);

        return pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente not founded with cpf: " + cpf));
    }

    public List<PacienteEntity> findAllPacientes(){
        LOGGER.info("getting all pacientes");
        return pacienteRepository.findAll();
    }

    public void updateSexoPacienteByCpf(String cpf, PacienteDTO pacienteDTO){
        PacienteEntity paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente not founded with cpf number: " + cpf));

        if(pacienteDTO.getSexo() != null && pacienteDTO.getSexo() != ""){
            paciente.setSexo(pacienteDTO.getSexo());
            LOGGER.info("updating sexoPaciente -> {}", paciente.getSexo());
            pacienteRepository.save(paciente);
        } else{

        }
    }

    public void updateNomePacienteByCpf(String cpf, PacienteDTO pacienteDTO){
        PacienteEntity paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente not founded with cpf number: " + cpf));

        if(pacienteDTO.getNome() != null && pacienteDTO.getNome() != ""){
            paciente.setNomeCompleto(pacienteDTO.getNome());
            LOGGER.info("updating nomePaciente -> {}", paciente.getNomeCompleto());
            pacienteRepository.save(paciente);
        } else{
            throw new ObjectMissingRequirementsException("nome must not be null or empty");
        }
    }

    public void updateTelefonePacienteByCpf(String cpf, PacienteDTO pacienteDTO){
        PacienteEntity paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ObjectNotFoundException("Paciente not founded with cpf number: " + cpf));

        if(pacienteDTO.getTelefone() != null && pacienteDTO.getTelefone() != ""){
            paciente.setTelefone(pacienteDTO.getTelefone());
            LOGGER.info("updating telefonePaciente -> {}", paciente.getTelefone());
            pacienteRepository.save(paciente);
        } else{
            throw new ObjectMissingRequirementsException("telefone must not be null or empty");
        }
    }

    public void deletePacienteByCpf(String cpf){

        PacienteEntity pacienteEntity = pacienteRepository.findByCpfIgnoreCase(cpf);

        if(pacienteEntity != null){
            if(pacienteEntity.getConsultas().isEmpty()){
                LOGGER.info("Deleting Paciente by cpf = {}", cpf);
                pacienteRepository.delete(pacienteEntity);
            } else {
                throw new RuntimeException("Paciente is being used in Consulta. Cannot delete it!");
            }
        } else {
            throw new RuntimeException("Paciente not founded with cpf number");
        }
    }
}
