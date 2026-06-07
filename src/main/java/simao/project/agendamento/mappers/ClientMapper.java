package simao.project.agendamento.mappers;

import org.mapstruct.Mapper;
import simao.project.agendamento.dtos.client.ClientRequestDto;
import simao.project.agendamento.dtos.client.ClientResponseDto;
import simao.project.agendamento.entitys.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    Client toEntity(ClientRequestDto clientRequestDto);
    ClientResponseDto toDto(Client client);
}
