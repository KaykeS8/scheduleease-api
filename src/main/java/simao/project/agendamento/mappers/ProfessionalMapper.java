package simao.project.agendamento.mappers;

import org.mapstruct.Mapper;
import simao.project.agendamento.dtos.professional.ProfessionalRequestDto;
import simao.project.agendamento.dtos.professional.ProfessionalResponseDto;
import simao.project.agendamento.entitys.Professional;

@Mapper(componentModel = "spring")
public interface ProfessionalMapper {
    Professional toEntity(ProfessionalRequestDto professionalRequestDto);
    ProfessionalResponseDto toDto(Professional professional);
}
