package simao.project.agendamento.dtos.serviceItem;


import java.math.BigDecimal;

public record ServiceItemResponseDto(
        Long id,
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price
) {
}
