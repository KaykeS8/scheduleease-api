package simao.project.agendamento.dtos.client;

import jakarta.validation.constraints.NotBlank;

public record ClientRequestDto(
    @NotBlank String name,
    @NotBlank String email,
    @NotBlank String phone
){
}
