package simao.project.agendamento.dtos.serviceItem;


import java.math.BigDecimal;

public record ServiceItemUpdateDto(
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price
) {
}
