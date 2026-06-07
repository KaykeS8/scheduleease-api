package simao.project.agendamento.dtos.scheduling;

import simao.project.agendamento.entitys.Scheduling;
import simao.project.agendamento.entitys.enums.StatusScheduling;

import java.time.LocalDateTime;

public record SchedulingResponseDto(
        Long id,
        LocalDateTime startDateTime,
        LocalDateTime endDateTime,
        StatusScheduling status,
        String observation,
        String clientName,
        String professionalName,
        String serviceItemDescription

) {
    public static SchedulingResponseDto toDto(Scheduling scheduling) {
        return new SchedulingResponseDto(
        scheduling.getId(),
        scheduling.getStartDateTime(),
        scheduling.getEndDateTime(),
        scheduling.getStatus(),
        scheduling.getObservation(),
        scheduling.getClient().getName(),
        scheduling.getProfessional().getName(),
        scheduling.getServiceItem().getName()
    );
    }
}
