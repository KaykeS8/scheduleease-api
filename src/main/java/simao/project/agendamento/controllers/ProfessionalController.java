package simao.project.agendamento.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simao.project.agendamento.dtos.professional.ProfessionalRequestDto;
import simao.project.agendamento.dtos.professional.ProfessionalResponseDto;
import simao.project.agendamento.dtos.professional.ProfessionalUpdateDto;
import simao.project.agendamento.services.ProfessionalService;

import java.util.List;

@RestController
@RequestMapping("api/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalResponseDto>> getAllProfessionals() {
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.getAllProfessionals());
    }

    @PostMapping
    public ResponseEntity<ProfessionalResponseDto> createProfessional(@RequestBody @Valid ProfessionalRequestDto professionalRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalService.createProfessional(professionalRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDto> getProfessionalById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.getProfessionalById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalResponseDto> updateProfessional(@RequestBody ProfessionalUpdateDto professionalUpdateDto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(professionalService.updateProfessional(professionalUpdateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable Long id) {
        professionalService.deleteProfessional(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}