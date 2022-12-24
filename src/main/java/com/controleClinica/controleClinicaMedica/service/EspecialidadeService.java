package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.EspecialidadeDTO;
import com.controleClinica.controleClinicaMedica.entities.EspecialidadeEntity;
import com.controleClinica.controleClinicaMedica.entities.MedicoEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectAlreadyCreatedException;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import com.controleClinica.controleClinicaMedica.repository.EspecialidadeRepository;
import com.controleClinica.controleClinicaMedica.repository.MedicoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.*;

@Service
public class EspecialidadeService {

    private static final Logger LOGGER = LogManager.getLogger(EspecialidadeService.class);

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    public void createEspecialidade(EspecialidadeDTO especialidadeDTO) {

        EspecialidadeEntity especialidadeEntity = new EspecialidadeEntity(especialidadeDTO.getNomeEspecialidade());

        if (especialidadeRepository.findByNomeEspecialidadeIgnoreCase(especialidadeEntity.getNomeEspecialidade()).isPresent()) {
            throw new ObjectAlreadyCreatedException(DEFAULT_ESPECILIDADE_EXCEPTION_MESSAGE + " - " + especialidadeDTO.getNomeEspecialidade());
        } else {
            LOGGER.info("creating Especialidade");
            especialidadeRepository.save(especialidadeEntity);
        }
    }

    public EspecialidadeEntity findEspecialidadeById(Integer id) {
        LOGGER.info("getting Especialidade by id = {}", id);
        return especialidadeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Especialidade not founded with id number: " + id));
    }

    public List<EspecialidadeEntity> findAllEspecialidades() {
        LOGGER.info("creating all Especialidades");
        return especialidadeRepository.findAll();
    }

    public void updateNomeEspecialidadeById(Integer id, EspecialidadeDTO especialidadeDTO) {

        EspecialidadeEntity especialidade = especialidadeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Especialidade not founded with id number: " + id));

        especialidade.setNomeEspecialidade(especialidadeDTO.getNomeEspecialidade());
        LOGGER.info("updating nomeEspecialidade: {}", especialidadeDTO.getNomeEspecialidade());
        especialidadeRepository.save(especialidade);
    }

    public void deleteEspecialidadeById(Integer idEspecialidade) {

        EspecialidadeEntity especialidade = especialidadeRepository.findByIdEspecialidade(idEspecialidade);

        if(especialidade != null){
            List<MedicoEntity> medicosCadastradosComEspecialidade = medicoRepository.findMedicoEntitiesByEspecialidadesIdEspecialidade(idEspecialidade);

            if(medicosCadastradosComEspecialidade.isEmpty()){
                LOGGER.info("Deleting Especialidade by Id: {}", idEspecialidade );
                especialidadeRepository.deleteById(idEspecialidade);
            } else {
                throw new RuntimeException("Especialidade is being used. Cannot delete it!");
            }
        } else {
            throw new RuntimeException("Especialidade not founded with id number");
        }
    }
}
