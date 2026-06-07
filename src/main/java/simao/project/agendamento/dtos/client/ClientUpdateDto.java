package simao.project.agendamento.dtos.client;

public record ClientUpdateDto(
        String name,
        String email,
        String phone
) {
}
