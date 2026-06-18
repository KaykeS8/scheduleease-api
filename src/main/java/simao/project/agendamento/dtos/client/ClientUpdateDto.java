package simao.project.agendamento.dtos.client;

import jakarta.validation.constraints.Email;

public record ClientUpdateDto(
        String name,
        @Email String email,
        String phone
) {
}
