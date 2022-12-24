package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.dto.ReceitaDTO;
import com.controleClinica.controleClinicaMedica.entities.ConsultaEntity;
import com.controleClinica.controleClinicaMedica.entities.MedicamentoEntity;
import com.controleClinica.controleClinicaMedica.entities.ReceitaEntity;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectMissingRequirementsException;
import com.controleClinica.controleClinicaMedica.repository.ConsultaRepository;
import com.controleClinica.controleClinicaMedica.repository.MedicamentoRepository;
import com.controleClinica.controleClinicaMedica.repository.ReceitaRepository;
import com.controleClinica.controleClinicaMedica.exceptions.ObjectNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.DEFAULT_CONSULTA_EXCEPTION_NOT_FOUNDED;
import static com.controleClinica.controleClinicaMedica.constants.ApplicationConstants.DEFAULT_MEDICAMENTO_EXCEPTION_NOT_FOUNDED;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    private static final Logger LOGGER = LogManager.getLogger(ReceitaService.class);

    public void createReceita(ReceitaDTO receitaDTO){

        ConsultaEntity consulta = consultaRepository.findByIdConsulta(receitaDTO.getIdConsulta());

        List<MedicamentoEntity> listaMedicamentos = new ArrayList<>();

        for(int i=0; i < receitaDTO.getIdMedicamentos().size(); i++){

            MedicamentoEntity medicamentoEntity = medicamentoRepository.findByIdMedicamento(receitaDTO.getIdMedicamentos().get(i));

            if (medicamentoEntity != null) {
                listaMedicamentos.add(medicamentoEntity);
            }
        }

        ReceitaEntity receitaEntity = new ReceitaEntity();

        receitaEntity.setDataEmissao(receitaDTO.getDataEmissao());
        receitaEntity.setDescricaoReceita(receitaDTO.getDescricao());
        receitaEntity.setConsulta(consulta);
        receitaEntity.setMedicamentos(listaMedicamentos);

        if(receitaEntity.getConsulta() == null){
            throw new ObjectMissingRequirementsException("Cannot create Receita with null values for Consulta");

        } else if (receitaEntity.getMedicamentos().isEmpty()) {
            throw new ObjectMissingRequirementsException("Cannot create Receita with null values for Medicamentos");

        } else {
            LOGGER.info("creating receita");
            receitaRepository.save(receitaEntity);
        }
    }

    public ReceitaEntity findReceitaById(Integer id){
        return receitaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Receita not founded with id number: " + id));
    }

    public List<ReceitaEntity> findAllReceitas(){
        return receitaRepository.findAll();
    }

    public void updateDataEmissaoReceitaById(Integer id, ReceitaDTO receitaDTO){
        ReceitaEntity receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Receita not founded with id number: " + id));

        if(receitaDTO.getDataEmissao() != null){
            receita.setDataEmissao(receitaDTO.getDataEmissao());
            LOGGER.info("updating DataEmissao");
            receitaRepository.save(receita);
        }
    }

    public void updateDescricaoReceitaById(Integer id, ReceitaDTO receitaDTO){
        ReceitaEntity receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Receita not founded with id number: " + id));

        if(receitaDTO.getDescricao() != null && receitaDTO.getDescricao() != ""){
            receita.setDescricaoReceita(receitaDTO.getDescricao());
            LOGGER.info("updating descrição");
            receitaRepository.save(receita);
        }
    }

    public void updateMedicamentoReceitaById(Integer id, ReceitaDTO receitaDTO){
        ReceitaEntity receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Receita not founded with id number: " + id));

        List<MedicamentoEntity> medicamentosAtualizados = new ArrayList<>();

        for(int i=0; i < receitaDTO.getIdMedicamentos().size(); i++){
             MedicamentoEntity medicamento = medicamentoRepository.findByIdMedicamento(receitaDTO.getIdMedicamentos().get(i));
             if(medicamento != null){
                 medicamentosAtualizados.add(medicamento);
             } else {
                 throw new ObjectNotFoundException(DEFAULT_MEDICAMENTO_EXCEPTION_NOT_FOUNDED + " with id number");
             }
        }

        if(!medicamentosAtualizados.isEmpty()){
            receita.setMedicamentos(medicamentosAtualizados);
            LOGGER.info("updating medicamentos");
            receitaRepository.save(receita);
        }
    }

    public void updateConsultaReceitaById(Integer id, ReceitaDTO receitaDTO){
        ReceitaEntity receita = receitaRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Receita not founded with id number: " + id));

        ConsultaEntity consulta = consultaRepository.findByIdConsulta(receitaDTO.getIdConsulta());

        if(consulta != null){
            receita.setConsulta(consulta);
            LOGGER.info("updating consulta");
            receitaRepository.save(receita);
        } else {
            throw new ObjectNotFoundException(DEFAULT_CONSULTA_EXCEPTION_NOT_FOUNDED);
        }
    }

    public void deleteReceitaById(Integer idReceita){

        ReceitaEntity receitaEntity = receitaRepository.findByIdReceita(idReceita);

        if(receitaEntity != null){
                if(!receitaEntity.getMedicamentos().isEmpty()){
                    receitaRepository.deleteReceitaMedicamentoByIdReceita(idReceita);
                    LOGGER.info("deleting receita by id: {}", idReceita);
                    receitaRepository.delete(receitaEntity);
                } else {
                    LOGGER.info("deleting receita by id: {}", idReceita);
                    receitaRepository.delete(receitaEntity);
                }
        } else {
            throw new RuntimeException("Receita not founded with id number");
        }

    }

}
