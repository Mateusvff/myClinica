package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.MedicamentoDTO;
import com.controleClinica.controleClinicaMedica.entities.MedicamentoEntity;
import com.controleClinica.controleClinicaMedica.entities.ReceitaEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectAlreadyCreatedException;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectMissingRequirementsException;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotAllowedToDeleteException;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import com.controleClinica.controleClinicaMedica.repository.MedicamentoRepository;
import com.controleClinica.controleClinicaMedica.repository.ReceitaRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.*;

@Service
public class MedicamentoService {

    private static final Logger LOGGER = LogManager.getLogger(MedicamentoService.class);

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    public void createMedicamento(MedicamentoDTO medicamentoDTO){

        MedicamentoEntity medicamentoEntity = new MedicamentoEntity();

        medicamentoEntity.setNomeTecnico(medicamentoDTO.getNomeMedicamento());
        medicamentoEntity.setDosagem(medicamentoDTO.getDosagem());
        medicamentoEntity.setLaboratorio(medicamentoDTO.getLaboratorio());

        if(medicamentoRepository.findByNomeTecnicoIgnoreCase(medicamentoDTO.getNomeMedicamento()).isPresent()){
            throw new ObjectAlreadyCreatedException(DEFAULT_MEDICAMENTO_EXCEPTION_MESSAGE + medicamentoDTO.getNomeMedicamento());
        } else {
            LOGGER.info("creating medicamento");
            medicamentoRepository.save(medicamentoEntity);
        }
    }

    public MedicamentoEntity findMedicamentoById(Integer id){
        LOGGER.info("getting medicamento By id: {}", id);

        return medicamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Medicamento not founded with id number: " + id));
    }

    public List<MedicamentoEntity> findAllMedicamentos(){
        LOGGER.info("getting all medicamentos");

        return medicamentoRepository.findAll();
    }

    public void updateNomeMedicamentoById(Integer id, MedicamentoDTO medicamentoDTO){
        MedicamentoEntity medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Medicamento not founded with id number: " + id));

        if(medicamentoDTO.getNomeMedicamento() != null && medicamentoDTO.getNomeMedicamento() != ""){
            medicamento.setNomeTecnico(medicamentoDTO.getNomeMedicamento());
            LOGGER.info("updating nomeMedicamento: {}", medicamento.getNomeTecnico());
            medicamentoRepository.save(medicamento);
        } else {
            throw new ObjectMissingRequirementsException("NomeMedicamento must not be null or empty");
        }
    }

    public void updateDosagemMedicamentoById(Integer id, MedicamentoDTO medicamentoDTO){
        MedicamentoEntity medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Medicamento not founded with id number: " + id));

        if(medicamentoDTO.getDosagem() != null && medicamentoDTO.getDosagem() != ""){
            medicamento.setDosagem(medicamentoDTO.getDosagem());
            LOGGER.info("updating dosagem: {}", medicamento.getDosagem());
            medicamentoRepository.save(medicamento);
        } else {
            throw new ObjectMissingRequirementsException("dosagem must not be null or empty");
        }
    }

    public void updateLaboratorioMedicamentoById(Integer id, MedicamentoDTO medicamentoDTO){
        MedicamentoEntity medicamento = medicamentoRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Medicamento not founded with id number: " + id));

        if(medicamentoDTO.getLaboratorio() != null && medicamentoDTO.getLaboratorio() != ""){
            medicamento.setLaboratorio(medicamentoDTO.getLaboratorio());
            LOGGER.info("updating laboratório: {}", medicamento.getLaboratorio());
            medicamentoRepository.save(medicamento);
        } else {
            throw new ObjectMissingRequirementsException("laboratório must not be null or empty");
        }
    }

    public void deleteMedicamentoById(Integer idMedicamento){

        if(medicamentoRepository.findByIdMedicamento(idMedicamento) != null){
            List<ReceitaEntity> receitas = receitaRepository.findReceitaEntitiesByMedicamentosIdMedicamento(idMedicamento);

            if(receitas.isEmpty()){
                medicamentoRepository.deleteById(idMedicamento);
            } else {
                throw new ObjectNotAllowedToDeleteException(DEFAULT_OBJECT_NOT_ALLOWED_TO_DELETE);
            }
        } else {
            throw new ObjectNotFoundException(DEFAULT_MEDICAMENTO_EXCEPTION_NOT_FOUNDED);
        }
    }
}
