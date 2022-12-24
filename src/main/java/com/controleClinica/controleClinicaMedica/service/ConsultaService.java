package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.ConsultaDTO;
import com.controleClinica.controleClinicaMedica.entities.ConsultaEntity;
import com.controleClinica.controleClinicaMedica.entities.MedicoEntity;
import com.controleClinica.controleClinicaMedica.entities.PacienteEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectMissingRequirementsException;
import com.controleClinica.controleClinicaMedica.repository.ConsultaRepository;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import com.controleClinica.controleClinicaMedica.repository.MedicoRepository;
import com.controleClinica.controleClinicaMedica.repository.PacienteRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.DEFAULT_CONSULTA_EXCEPTION_NOT_FOUNDED;

@Service
public class ConsultaService {

    private static final Logger LOGGER = LogManager.getLogger(ConsultaService.class);

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public void createConsulta(ConsultaDTO consultaDTO){

        ConsultaEntity consultaEntity = new ConsultaEntity();

        consultaEntity.setMedico(medicoRepository.findByCrmAndSiglaEstadoIgnoreCase(consultaDTO.getCrmMedico(), consultaDTO.getSiglaEstadoMedico()));
        consultaEntity.setPaciente(pacienteRepository.findByCpfIgnoreCase(consultaDTO.getCpfPaciente()));

        if(consultaEntity.getMedico() == null){
            throw new ObjectMissingRequirementsException("Cannot create Consulta with null values for Medico");

        } else if (consultaEntity.getPaciente() == null) {
            throw new ObjectMissingRequirementsException("Cannot create Consulta with null values for Paciente");

        } else {
            LOGGER.info("creating consulta");
            consultaRepository.save(consultaEntity);
        }
    }

    public ConsultaEntity findConsultaById(Integer id){

        return consultaRepository.findConsultaEntityByIdConsulta(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta not founded with id number: " + id));
    }

    public List<ConsultaEntity> findAllConsultas(){
        return consultaRepository.findAll();
    }

    public void updateMedicoConsultaById(Integer id, ConsultaDTO consultaDTO){
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta not founded with id number: " + id));

        if(consulta.getMedico() != null && consultaDTO.getSiglaEstadoMedico() != null){
            MedicoEntity medico = medicoRepository.findByCrmAndSiglaEstadoIgnoreCase(consultaDTO.getCrmMedico(), consultaDTO.getSiglaEstadoMedico());
            consulta.setMedico(medico);

            LOGGER.info("Updating medico: crm = {}, siglaEstado = {}", medico.getCrm(), medico.getSiglaEstado());

            consultaRepository.save(consulta);
        } else {
            throw new ObjectMissingRequirementsException("Crm or SiglaEstado must not be null values");
        }
    }

    public void updatePacienteConsultaById(Integer id, ConsultaDTO consultaDTO){
        ConsultaEntity consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Consulta not founded with id number: " + id));

        if(consultaDTO.getCpfPaciente() != null && consultaDTO.getCpfPaciente() != ""){
            PacienteEntity paciente = pacienteRepository.findByCpfIgnoreCase(consultaDTO.getCpfPaciente());
            consulta.setPaciente(paciente);

            LOGGER.info("Updating paciente: cpf = {}", paciente.getCpf());

            consultaRepository.save(consulta);
        } else {
            throw new ObjectMissingRequirementsException("cpf must not be a null value for paciente");
        }
    }

    public void deleteConsultaById(Integer idConsulta) {

        ConsultaEntity consultaEntity = consultaRepository.findByIdConsulta(idConsulta);

        if(consultaEntity != null){
            LOGGER.info("Deleting consulta by id = {}", idConsulta);
            consultaRepository.delete(consultaEntity);
        } else {
            throw new ObjectNotFoundException(DEFAULT_CONSULTA_EXCEPTION_NOT_FOUNDED);
        }
    }
}
