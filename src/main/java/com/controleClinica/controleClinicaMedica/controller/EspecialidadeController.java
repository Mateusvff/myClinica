package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.EspecialidadeDTO;
import com.controleClinica.controleClinicaMedica.entities.EspecialidadeEntity;
import com.controleClinica.controleClinicaMedica.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clinica/especialidade")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @PostMapping ("/create")
    public ResponseEntity<?> createEspecialidade(@RequestBody EspecialidadeDTO especialidade){

        especialidadeService.createEspecialidade(especialidade);

        return ResponseEntity.ok().body(especialidade);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getEspecialidadeById(@PathVariable ("id") Integer id){

        EspecialidadeEntity especialidadeEntity = especialidadeService.findEspecialidadeById(id);

        return ResponseEntity.ok().body(especialidadeEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<EspecialidadeEntity>> getAllEspecialidades(){

        List<EspecialidadeEntity> allEspecialidades = especialidadeService.findAllEspecialidades();

        return ResponseEntity.ok().body(allEspecialidades);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateNomeEspecialidadeById(@PathVariable ("id") Integer id, @RequestBody EspecialidadeDTO especialidadeDTO){

        especialidadeService.updateNomeEspecialidadeById(id, especialidadeDTO);

        return ResponseEntity.ok().body("Nome especialidade has been updated: " + especialidadeDTO.getNomeEspecialidade());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEspecialidadeById(@PathVariable ("id") Integer id){

        especialidadeService.deleteEspecialidadeById(id);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
