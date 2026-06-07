package simao.project.agendamento.dtos.professional;

import simao.project.agendamento.entitys.Scheduling;

import java.util.List;

public record ProfessionalResponseDto(
        Long id,
        String name,
        String email,
        String specialty
) {
}
