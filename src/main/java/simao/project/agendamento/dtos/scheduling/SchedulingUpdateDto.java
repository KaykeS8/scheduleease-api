package simao.project.agendamento.dtos.scheduling;

import jakarta.validation.constraints.NotNull;
import simao.project.agendamento.entitys.enums.StatusScheduling;

public record SchedulingUpdateDto(
            @NotNull
            StatusScheduling status
) {
}
