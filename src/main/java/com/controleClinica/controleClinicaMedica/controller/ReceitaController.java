package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.ReceitaDTO;
import com.controleClinica.controleClinicaMedica.entities.ReceitaEntity;
import com.controleClinica.controleClinicaMedica.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clinica/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/create")
    public ResponseEntity<?> createReceita(@RequestBody ReceitaDTO receitaDTO){

        receitaService.createReceita(receitaDTO);

        return ResponseEntity.ok().body(receitaDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getReceitaById(@PathVariable ("id") Integer id){

        ReceitaEntity receitaEntity = receitaService.findReceitaById(id);

        return ResponseEntity.ok().body(receitaEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ReceitaEntity>> getAllReceitas(){

        List<ReceitaEntity> allReceitas = receitaService.findAllReceitas();

        return ResponseEntity.ok().body(allReceitas);
    }

    @PutMapping("/updateDataEmissao/{id}")
    public ResponseEntity<?> updateDataEmissaoReceitaById(@PathVariable ("id") Integer id,
                                                          @RequestBody ReceitaDTO receitaDTO){

        receitaService.updateDataEmissaoReceitaById(id, receitaDTO);

        return ResponseEntity.ok().body(receitaDTO);
    }

    @PutMapping("/updateDescricao/{id}")
    public ResponseEntity<?> updateDescricaoReceitaById(@PathVariable ("id") Integer id,
                                                        @RequestBody ReceitaDTO receitaDTO){

        receitaService.updateDescricaoReceitaById(id, receitaDTO);

        return ResponseEntity.ok().body(receitaDTO);
    }

    @PutMapping("/updateMedicamentos/{id}")
    public ResponseEntity<?> updateMedicamentosReceitaById(@PathVariable ("id") Integer id,
                                                           @RequestBody ReceitaDTO receitaDTO){

        receitaService.updateMedicamentoReceitaById(id, receitaDTO);

        return ResponseEntity.ok().body(receitaDTO);
    }

    @PutMapping("/updateConsulta/{id}")
    public ResponseEntity<?> updateConsultaReceitaById(@PathVariable ("id") Integer id,
                                                       @RequestBody ReceitaDTO receitaDTO){

        receitaService.updateConsultaReceitaById(id, receitaDTO);

        return ResponseEntity.ok().body(receitaDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReceitaById(@PathVariable("id") Integer id){

        receitaService.deleteReceitaById(id);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
