package simao.project.agendamento.dtos.serviceItem;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ServiceItemRequestDto(
        @NotBlank String name,
        String description,
        @NotNull @Positive Integer durationMinutes,
        @NotNull @Positive BigDecimal price
) {
}
