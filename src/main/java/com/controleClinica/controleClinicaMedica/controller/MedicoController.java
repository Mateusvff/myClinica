package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.MedicoDTO;
import com.controleClinica.controleClinicaMedica.entities.MedicoEntity;
import com.controleClinica.controleClinicaMedica.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clinica/medico")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @PostMapping("create")
    public ResponseEntity<?> createMedico(@RequestBody MedicoDTO medicoDto){

        medicoService.createMedico(medicoDto);

        return ResponseEntity.ok().body(medicoDto);
    }

    @GetMapping("/get/crm/{crm}/siglaEstado/{siglaEstado}")
    public ResponseEntity<?> getMedicoByCrmAndSiglaEstado(@PathVariable ("crm") Integer crm,
                                                          @PathVariable ("siglaEstado") String siglaEstado){

        MedicoEntity medicoEntity = medicoService.findMedicoByCrmAndSiglaEstado(crm, siglaEstado);

        return ResponseEntity.ok().body(medicoEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<MedicoEntity>> getAllMedicos(){

        List<MedicoEntity> allMedicos = medicoService.findAllMedicos();

        return ResponseEntity.ok().body(allMedicos);
    }

    @PutMapping("/updateNome/crm/{crm}/siglaEstado/{sigla}")
    public ResponseEntity<?> updateNomeMedicoByCrmAndSiglaEstado(@PathVariable ("crm") Integer crm,
                                                                 @PathVariable ("sigla") String sigla,
                                                                 @RequestBody MedicoDTO medicoDTO){

        medicoService.updateNomeCompletoMedicoByCrmAndSiglaEstado(crm, sigla, medicoDTO);

        return ResponseEntity.ok().body(medicoDTO);
    }

    @PutMapping("updateEspecialidades/crm/{crm}/siglaEstado/{sigla}")
    public ResponseEntity<?> updateEspecialidadesByCrmAndSiglaEstado(@PathVariable ("crm") Integer crm,
                                                                     @PathVariable ("sigla") String sigla,
                                                                     @RequestBody MedicoDTO medicoDTO){

        medicoService.updateEspecialidadeMedicoByCrmAndSiglaEstado(crm, sigla, medicoDTO);

        return ResponseEntity.ok().body(medicoDTO);
    }

    @DeleteMapping("/delete/crm/{crm}/siglaEstado/{siglaEstado}")
    public ResponseEntity<?> deleteMedicoByCrmAndSiglaEstado(@PathVariable("crm") Integer crm,
                                                             @PathVariable("siglaEstado") String siglaEstado){

        medicoService.deleteMedicoByCrmAndSiglaEstado(crm, siglaEstado);

        return ResponseEntity.ok().body(HttpStatus.OK);

    }


}
