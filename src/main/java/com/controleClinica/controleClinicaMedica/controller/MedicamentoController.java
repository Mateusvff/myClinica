package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.MedicamentoDTO;
import com.controleClinica.controleClinicaMedica.entities.MedicamentoEntity;
import com.controleClinica.controleClinicaMedica.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clinica/medicamento")
public class MedicamentoController {

    @Autowired
    private MedicamentoService medicamentoService;

    @PostMapping("/create")
    public ResponseEntity<?> createMedicamento(@RequestBody MedicamentoDTO medicamentoDTO){

        medicamentoService.createMedicamento(medicamentoDTO);

        return ResponseEntity.ok().body(medicamentoDTO);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getMedicamentoById(@PathVariable("id") Integer id){

        MedicamentoEntity medicamentoEntity = medicamentoService.findMedicamentoById(id);

        return ResponseEntity.ok().body(medicamentoEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicamentoEntity>> getAllMedicamentos(){

        List<MedicamentoEntity> allMedicamentos = medicamentoService.findAllMedicamentos();

        return ResponseEntity.ok().body(allMedicamentos);
    }

    @PutMapping("/updateNomeMedicamento/{id}")
    public ResponseEntity<?> updateNomeMedicamento(@PathVariable ("id") Integer id,
                                                   @RequestBody MedicamentoDTO medicamentoDTO){

        medicamentoService.updateNomeMedicamentoById(id, medicamentoDTO);

        return ResponseEntity.ok().body(medicamentoDTO);
    }

    @PutMapping("/updateDosagem/{id}")
    public ResponseEntity<?> updateDosagemMedicamento(@PathVariable ("id") Integer id,
                                                      @RequestBody MedicamentoDTO medicamentoDTO){

        medicamentoService.updateDosagemMedicamentoById(id, medicamentoDTO);

        return ResponseEntity.ok().body(medicamentoDTO);
    }

    @PutMapping("/updateLaboratorio/{id}")
    public ResponseEntity<?> updateLaboratorioMedicamento(@PathVariable ("id") Integer id,
                                                          @RequestBody MedicamentoDTO medicamentoDTO){

        medicamentoService.updateLaboratorioMedicamentoById(id, medicamentoDTO);

        return ResponseEntity.ok().body(medicamentoDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMedicamentoById(@PathVariable ("id") Integer idMedicamento){

        medicamentoService.deleteMedicamentoById(idMedicamento);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
