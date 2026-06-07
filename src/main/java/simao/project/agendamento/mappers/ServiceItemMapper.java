package simao.project.agendamento.mappers;

import org.mapstruct.Mapper;
import simao.project.agendamento.dtos.serviceItem.ServiceItemRequestDto;
import simao.project.agendamento.dtos.serviceItem.ServiceItemResponseDto;
import simao.project.agendamento.entitys.ServiceItem;

@Mapper(componentModel = "spring")
public interface ServiceItemMapper {
    ServiceItem toEntity(ServiceItemRequestDto serviceItemRequestDto);
    ServiceItemResponseDto toDto(ServiceItem serviceItem);
}
