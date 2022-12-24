package com.controleClinica.controleClinicaMedica.service;

import com.controleClinica.controleClinicaMedica.entities.*;
import com.controleClinica.controleClinicaMedica.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataBaseService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public void instantiateDataBase() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        EspecialidadeEntity especialidade1 = new EspecialidadeEntity("Cardiologista");
        EspecialidadeEntity especialidade2 = new EspecialidadeEntity("Endocrinologista");
        EspecialidadeEntity especialidade3 = new EspecialidadeEntity("Ortopedista");
        EspecialidadeEntity especialidade4 = new EspecialidadeEntity("Clínico Geral");
        EspecialidadeEntity especialidade5 = new EspecialidadeEntity("Dermatologista");

        especialidadeRepository.saveAll(Arrays.asList(especialidade1, especialidade2, especialidade3, especialidade4,
                especialidade5));

        List<EspecialidadeEntity> especialidadesMedico1 = new ArrayList<>();
        especialidadesMedico1.add(especialidade1);
        especialidadesMedico1.add(especialidade4);

        List<EspecialidadeEntity> especialidadesMedico2 = new ArrayList<>();
        especialidadesMedico2.add(especialidade2);
        especialidadesMedico2.add(especialidade3);

        MedicoEntity medico1 = new MedicoEntity(123456, "DF", "Mateus Vinícius",
                especialidadesMedico1);

        MedicoEntity medico2 = new MedicoEntity(987654, "SP", "Breno Henrique",
                especialidadesMedico2);

        medicoRepository.saveAll(Arrays.asList(medico1, medico2));

        PacienteEntity paciente1 = new PacienteEntity("02939489688", "Feminino", "Liliam Franco",
                "61991242527");

        PacienteEntity paciente2 = new PacienteEntity("41021479187", "Masculino", "Paulo Franco",
                "6198565809");

        pacienteRepository.saveAll(Arrays.asList(paciente1, paciente2));

        MedicamentoEntity medicamento1 = new MedicamentoEntity("Ibuprofeno", "5mg", "Medley");
        MedicamentoEntity medicamento2 = new MedicamentoEntity("Isotretinoína", "20mg", "Fármaco");

        medicamentoRepository.saveAll(Arrays.asList(medicamento1, medicamento2));

        List<MedicamentoEntity> medicamentosReceita1 = new ArrayList<>();
        medicamentosReceita1.add(medicamento1);

        List<MedicamentoEntity> medicamentosReceita2 = new ArrayList<>();
        medicamentosReceita2.add(medicamento2);

        ConsultaEntity consulta1 = new ConsultaEntity(medico1, paciente1);
        ConsultaEntity consulta2 = new ConsultaEntity(medico2, paciente2);

        consultaRepository.saveAll(Arrays.asList(consulta1, consulta2));

        ReceitaEntity receita1 = new ReceitaEntity(simpleDateFormat.parse("14/10/2022 00:00"),
                "Receita1", medicamentosReceita1, consulta1);

        ReceitaEntity receita2 = new ReceitaEntity(simpleDateFormat.parse("14/10/2022 00:00"),
                "Receita2", medicamentosReceita2, consulta2 );

        receitaRepository.saveAll(Arrays.asList(receita1, receita2));
    }
}
