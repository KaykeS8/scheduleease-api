package simao.project.agendamento.dtos.client;

public record ClientResponseDto(
        Long id,
        String name,
        String email,
        String phone
) {
}
