package simao.project.agendamento.dtos.professional;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessionalRequestDto(
        @NotBlank String name,
        @NotBlank String specialty,
        @NotBlank @Email String email
) {
}