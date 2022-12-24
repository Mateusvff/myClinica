package com.controleClinica.controleClinicaMedica.controller;

import com.controleClinica.controleClinicaMedica.dto.PacienteDTO;
import com.controleClinica.controleClinicaMedica.entities.PacienteEntity;
import com.controleClinica.controleClinicaMedica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clinica/paciente")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @PostMapping("/create")
    public ResponseEntity<?> createPaciente(@RequestBody PacienteDTO pacienteDTO){

        pacienteService.createPaciente(pacienteDTO);

        return ResponseEntity.ok().body(pacienteDTO);
    }

    @GetMapping("get/{cpf}")
    public ResponseEntity<?> getPacienteByCpf(@PathVariable("cpf") String cpf){

      PacienteEntity pacienteEntity =  pacienteService.findPacienteByCpf(cpf);

      return ResponseEntity.ok().body(pacienteEntity);
    }

    @GetMapping("getAll")
    public ResponseEntity<List<PacienteEntity>> getAllPacientes (){

        List<PacienteEntity> allPacientes = pacienteService.findAllPacientes();

        return ResponseEntity.ok().body(allPacientes);
    }

    @PutMapping("/updateSexo/{cpf}")
    public ResponseEntity<?> updateSexoPacienteById(@PathVariable ("cpf") String cpf,
                                                    @RequestBody PacienteDTO pacienteDTO){

        pacienteService.updateSexoPacienteByCpf(cpf, pacienteDTO);

        return ResponseEntity.ok().body(pacienteDTO);
    }

    @PutMapping("/updateNome/{cpf}")
    public ResponseEntity<?> updateNomePacienteByCpf(@PathVariable ("cpf") String cpf,
                                                     @RequestBody PacienteDTO pacienteDTO){

        pacienteService.updateNomePacienteByCpf(cpf, pacienteDTO);

        return ResponseEntity.ok().body(pacienteDTO);
    }

    @PutMapping("/updateTelefone/{cpf}")
    public ResponseEntity<?> updateTelefonePacienteByCpf(@PathVariable ("cpf") String cpf,
                                                         @RequestBody PacienteDTO pacienteDTO){

        pacienteService.updateTelefonePacienteByCpf(cpf, pacienteDTO);

        return ResponseEntity.ok().body(pacienteDTO);
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<?> deletePacienteByCpf(@PathVariable("cpf") String cpf){

        pacienteService.deletePacienteByCpf(cpf);

        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
