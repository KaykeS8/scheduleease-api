package simao.project.agendamento.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import simao.project.agendamento.dtos.scheduling.SchedulingRequestDto;
import simao.project.agendamento.dtos.scheduling.SchedulingResponseDto;
import simao.project.agendamento.entitys.Scheduling;

@Mapper(componentModel = "spring")
public interface SchedulingMapper {
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "professional", ignore = true)
    @Mapping(target = "serviceItem", ignore = true)
    @Mapping(target = "endDateTime", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Scheduling toEntity(SchedulingRequestDto schedulingRequestDto);
    SchedulingResponseDto toDto(Scheduling scheduling);
}
