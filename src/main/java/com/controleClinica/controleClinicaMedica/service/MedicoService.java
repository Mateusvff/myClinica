package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.MedicoDTO;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectAlreadyCreatedException;
import com.controleClinica.controleClinicaMedica.entities.EspecialidadeEntity;
import com.controleClinica.controleClinicaMedica.entities.MedicoEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectMissingRequirementsException;
import com.controleClinica.controleClinicaMedica.repository.EspecialidadeRepository;
import com.controleClinica.controleClinicaMedica.repository.MedicoRepository;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.DEFAULT_ESPECILIDADE_EXCEPTION_NOT_FOUNDED;
import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.DEFAULT_MEDICO_EXCEPTION_MESSAGE;

@Service
public class MedicoService {

    private static final Logger LOGGER = LogManager.getLogger(MedicoService.class);

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public void createMedico(MedicoDTO medicoDto) {

        List<EspecialidadeEntity> listaEspecialidade = new ArrayList<>();

        for(int i=0; i < medicoDto.getIdEspecialidades().size(); i++){

            EspecialidadeEntity especialidadeEntity = especialidadeRepository.findByIdEspecialidade(medicoDto.getIdEspecialidades().get(i));

            if(especialidadeEntity != null){
                listaEspecialidade.add(especialidadeEntity);
            } else {
                throw new ObjectNotFoundException(DEFAULT_ESPECILIDADE_EXCEPTION_NOT_FOUNDED);
            }
        }

        MedicoEntity medicoEntity = new MedicoEntity();

        medicoEntity.setCrm(medicoDto.getCrm());
        medicoEntity.setNomeCompleto(medicoDto.getNomeCompleto());
        medicoEntity.setSiglaEstado(medicoDto.getSiglaEstado());
        medicoEntity.setEspecialidades(listaEspecialidade);

        if(medicoRepository.findByCrmAndSiglaEstado(medicoEntity.getCrm(), medicoEntity.getSiglaEstado()).isPresent()){
            throw new ObjectAlreadyCreatedException(DEFAULT_MEDICO_EXCEPTION_MESSAGE + medicoEntity);

        } else{
            LOGGER.info("creating medico: crm = {}, sigla = {}", medicoEntity.getCrm(), medicoEntity.getSiglaEstado());
            medicoRepository.save(medicoEntity);
        }
    }

    public MedicoEntity findMedicoByCrmAndSiglaEstado(Integer crm, String siglaEstado){
        LOGGER.info("getting medico: crm = {}, sigla = {}", crm, siglaEstado);

        return medicoRepository.findByCrmAndSiglaEstado(crm, siglaEstado)
                .orElseThrow(() -> new ObjectNotFoundException("Medico not founded with CRM and siglaEstado"));
    }

    public List<MedicoEntity> findAllMedicos(){

        LOGGER.info("getting all medicos");

        return medicoRepository.findAll();
    }

    public void updateNomeCompletoMedicoByCrmAndSiglaEstado(Integer crm, String sigla, MedicoDTO medicoDTO){
        MedicoEntity medico = medicoRepository.findByCrmAndSiglaEstado(crm, sigla)
                .orElseThrow(() -> new ObjectNotFoundException("Medico not founded with crm and sigla: " + crm + sigla));

        if(medicoDTO.getNomeCompleto() != null && medicoDTO.getNomeCompleto() != ""){
            medico.setNomeCompleto(medicoDTO.getNomeCompleto());

            LOGGER.info("updating nomeMedico to {}", medico.getNomeCompleto());
            medicoRepository.save(medico);
        } else {
            throw new ObjectMissingRequirementsException("nome must not be null or empty");
        }
    }

    public void updateEspecialidadeMedicoByCrmAndSiglaEstado(Integer crm, String sigla, MedicoDTO medicoDTO){
        MedicoEntity medico = medicoRepository.findByCrmAndSiglaEstado(crm, sigla)
                .orElseThrow(() -> new ObjectNotFoundException("Medico not founded with crm and sigla: " + crm + sigla));

        List<EspecialidadeEntity> especialidadesAtualizadas = new ArrayList<>();

        for(int i=0; i < medicoDTO.getIdEspecialidades().size(); i++){

            EspecialidadeEntity especialidade = especialidadeRepository.findByIdEspecialidade(medicoDTO.getIdEspecialidades().get(i));

            if(especialidade != null){
                especialidadesAtualizadas.add(especialidade);
            }
        }

        if(!especialidadesAtualizadas.isEmpty()){
            medico.setEspecialidades(especialidadesAtualizadas);

            LOGGER.info("updating especialidades");
            medicoRepository.save(medico);
        }
    }

    public void deleteMedicoByCrmAndSiglaEstado(Integer crm, String siglaEstado){

        MedicoEntity medicoEntity = medicoRepository.findByCrmAndSiglaEstadoIgnoreCase(crm, siglaEstado);

        if (medicoEntity != null){
            if(medicoEntity.getConsultas().isEmpty()){
                LOGGER.info("Deleting Medico...");
                medicoRepository.delete(medicoEntity);
            } else{
                throw new RuntimeException("MedicoEntity is being used in Consulta. Cannot delete it!");
            }

        } else {
            throw new RuntimeException("Medico not founded with crm and sigla");
        }

    }

}
