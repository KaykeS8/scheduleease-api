package simao.project.agendamento.dtos.scheduling;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record SchedulingRequestDto(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        @NotNull(message = "start date is required")
        @Future(message = "date should be in future")
        LocalDateTime startDateTime,

        String observation,

        @NotNull(message = "client ID is required")
        Long clientId,
        @NotNull(message = "professional ID is required")
        Long professionalId,
        @NotNull(message = "service item ID is required")
        Long serviceItemId
) {
}
