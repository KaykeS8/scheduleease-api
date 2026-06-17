package simao.project.agendamento.dtos.professional;

public record ProfessionalResponseDto(
        Long id,
        String name,
        String email,
        String specialty
) {
}
