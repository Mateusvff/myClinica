package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.ConsultaDTO;
import com.controleClinica.controleClinicaMedica.entities.ConsultaEntity;
import com.controleClinica.controleClinicaMedica.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/clinica/consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService consultaService;

    @PostMapping("/create")
    public ResponseEntity<?> createConsulta(@RequestBody ConsultaDTO consultaDto){

        consultaService.createConsulta(consultaDto);

        return ResponseEntity.ok().body(consultaDto);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getConsultaById(@PathVariable ("id") Integer id){

        ConsultaEntity consultaEntity = consultaService.findConsultaById(id);

        return ResponseEntity.ok().body(consultaEntity);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ConsultaEntity>> getAllConsultas(){

        List<ConsultaEntity> allConsultas = consultaService.findAllConsultas();

        return  ResponseEntity.ok().body(allConsultas);
    }

    @PutMapping("/updateMedico/{id}")
    public ResponseEntity<?> updateMedicoByConsultaId(@PathVariable("id") Integer id,
                                                      @RequestBody ConsultaDTO consultaDTO){

        consultaService.updateMedicoConsultaById(id, consultaDTO);

        return ResponseEntity.ok().body(consultaDTO);
    }

    @PutMapping("/updatePaciente/{id}")
    public ResponseEntity<?> updatePacienteByConsultaId(@PathVariable ("id") Integer id,
                                                        @RequestBody ConsultaDTO consultaDTO){

        consultaService.updatePacienteConsultaById(id, consultaDTO);

        return ResponseEntity.ok().body(consultaDTO);
    }

    @DeleteMapping("/delete/{idConsulta}")
    public ResponseEntity<?> deleteConsultaById(@PathVariable ("idConsulta") Integer idConsulta){

        consultaService.deleteConsultaById(idConsulta);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
