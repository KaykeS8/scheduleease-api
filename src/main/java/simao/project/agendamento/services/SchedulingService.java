package simao.project.agendamento.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import simao.project.agendamento.dtos.scheduling.SchedulingRequestDto;
import simao.project.agendamento.dtos.scheduling.SchedulingResponseDto;
import simao.project.agendamento.entitys.Client;
import simao.project.agendamento.entitys.Professional;
import simao.project.agendamento.entitys.Scheduling;
import simao.project.agendamento.entitys.ServiceItem;
import simao.project.agendamento.entitys.enums.StatusScheduling;
import simao.project.agendamento.exceptions.EntityNotFoundException;
import simao.project.agendamento.exceptions.NoAvailableTimeException;
import simao.project.agendamento.mappers.SchedulingMapper;
import simao.project.agendamento.repositories.ClientRepository;
import simao.project.agendamento.repositories.ProfessionalRepository;
import simao.project.agendamento.repositories.SchedulingRepository;
import simao.project.agendamento.repositories.ServiceItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SchedulingService {
    private final SchedulingRepository schedulingRepository;
    private final ClientRepository clientRepository;
    private final ProfessionalRepository professionalRepository;
    private final ServiceItemRepository serviceItemRepository;


    private final Logger log = LoggerFactory.getLogger(SchedulingService.class);
    private final SchedulingMapper mapper;

    public SchedulingService(SchedulingRepository schedulingRepository, SchedulingMapper mapper, ClientRepository clientRepository, ProfessionalRepository professionalRepository, ServiceItemRepository serviceItemRepository) {
        this.schedulingRepository = schedulingRepository;
        this.clientRepository = clientRepository;
        this.professionalRepository = professionalRepository;
        this.serviceItemRepository = serviceItemRepository;
        this.mapper = mapper;
    }

    public List<SchedulingResponseDto> getAllScheduling() {
        return schedulingRepository.findAll().stream().map(SchedulingResponseDto::toDto).toList();
    }

    @Transactional
    public SchedulingResponseDto createScheduling(SchedulingRequestDto schedulingRequestDto) {
        log.info("Creating scheduling");

        Client client = clientRepository.findById(schedulingRequestDto.clientId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + schedulingRequestDto.clientId()));

        Professional professional = professionalRepository.findById(schedulingRequestDto.professionalId())
                .orElseThrow(() -> new EntityNotFoundException("Professional not found with ID: " + schedulingRequestDto.professionalId()));

        ServiceItem serviceItem = serviceItemRepository.findById(schedulingRequestDto.serviceItemId())
                .orElseThrow(() -> new EntityNotFoundException("Service item not found with ID: " + schedulingRequestDto.serviceItemId()));

        LocalDateTime endDateTime = schedulingRequestDto.startDateTime().plusMinutes(serviceItem.getDurationMinutes());

        if (schedulingRepository.existDateConflict(schedulingRequestDto.professionalId(), schedulingRequestDto.startDateTime(), endDateTime)) {
            throw new NoAvailableTimeException("Time unavailable for professional: " + professional.getName());
        }


        Scheduling scheduling = new Scheduling();
        scheduling.setClient(client);
        scheduling.setServiceItem(serviceItem);
        scheduling.setProfessional(professional);

        scheduling.setStatus(StatusScheduling.SCHEDULED);
        scheduling.setObservation(schedulingRequestDto.observation());
        scheduling.setStartDateTime(schedulingRequestDto.startDateTime());
        scheduling.setEndDateTime(endDateTime);

        return SchedulingResponseDto.toDto(schedulingRepository.save(scheduling));
    }
}
