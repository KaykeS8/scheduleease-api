package simao.project.agendamento.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import simao.project.agendamento.dtos.professional.ProfessionalRequestDto;
import simao.project.agendamento.dtos.professional.ProfessionalResponseDto;
import simao.project.agendamento.dtos.professional.ProfessionalUpdateDto;
import simao.project.agendamento.entitys.Professional;
import simao.project.agendamento.exceptions.EntityNotFoundException;
import simao.project.agendamento.mappers.ProfessionalMapper;
import simao.project.agendamento.repositories.ProfessionalRepository;
import java.util.List;

@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final Logger log = LoggerFactory.getLogger(ProfessionalService.class);
    private final ProfessionalMapper mapper;

    public ProfessionalService(ProfessionalRepository professionalRepository, ProfessionalMapper mapper) {
        this.professionalRepository = professionalRepository;
        this.mapper = mapper;
    }

    public List<ProfessionalResponseDto> getAllProfessionals() {
        log.info("Get all professionals");
        return professionalRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public ProfessionalResponseDto createProfessional(ProfessionalRequestDto professionalRequestDto) {
        log.info("Creating professional");
        Professional professional = professionalRepository.save(mapper.toEntity(professionalRequestDto));
        return mapper.toDto(professional);
    }

    public ProfessionalResponseDto getProfessionalById(Long id) {
        log.info("Find professional by ID: {}", id);
        return professionalRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + id));
    }

    @Transactional
    public ProfessionalResponseDto updateProfessional(ProfessionalUpdateDto professionalUpdateDto, Long id) {
        log.info("Updating professional by ID: {}", id);
        return professionalRepository.findById(id)
                .map(professional -> {
                    if (professionalUpdateDto.name() != null) professional.setName(professionalUpdateDto.name());
                    if (professionalUpdateDto.email() != null) professional.setEmail(professionalUpdateDto.email());
                    if (professionalUpdateDto.specialty() != null) professional.setSpecialty(professionalUpdateDto.specialty());
                    return mapper.toDto(professionalRepository.save(professional));
                })
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + id));
    }

    public void deleteProfessional(Long id) {
        log.info("Deleting professional by ID: {}", id);
        if (!professionalRepository.existsById(id)) throw new EntityNotFoundException("Professional not found with ID: " + id);
        professionalRepository.deleteById(id);
    }
}
