package simao.project.agendamento.dtos.professional;

import jakarta.validation.constraints.Email;

public record ProfessionalUpdateDto(
        String name,
        @Email String email,
        String specialty
) {
}
