package simao.project.agendamento.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import simao.project.agendamento.dtos.client.ClientRequestDto;
import simao.project.agendamento.dtos.client.ClientResponseDto;
import simao.project.agendamento.dtos.client.ClientUpdateDto;
import simao.project.agendamento.entitys.Client;
import simao.project.agendamento.exceptions.EntityNotFoundException;
import simao.project.agendamento.mappers.ClientMapper;
import simao.project.agendamento.repositories.ClientRepository;
import java.util.List;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final Logger log = LoggerFactory.getLogger(ClientService.class);
    private final ClientMapper mapper;

    public ClientService(ClientRepository clientRepository, ClientMapper mapper) {
        this.clientRepository = clientRepository;
        this.mapper = mapper;
    }

    public List<ClientResponseDto> getAllClients() {
        log.info("Get all clients");
        return clientRepository.findAll().stream().map(mapper::toDto).toList();
    }

    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {
        log.info("Creating client");
        Client client = clientRepository.save(mapper.toEntity(clientRequestDto));
        return mapper.toDto(client);
    }

    public ClientResponseDto getClientById(Long id) {
        log.info("Finding client with ID: {}", id);
        return clientRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
    }

    public ClientResponseDto updateClient(ClientUpdateDto clientUpdateDto, Long id) {
        log.info("Updating client with ID: {}", id);
        return clientRepository.findById(id)
                .map(client -> {
                    if (clientUpdateDto.email() != null) client.setEmail(clientUpdateDto.email());
                    if (clientUpdateDto.name() != null) client.setName(clientUpdateDto.name());
                    if (clientUpdateDto.phone() != null) client.setPhone(clientUpdateDto.phone());
                    return mapper.toDto(clientRepository.save(client));
                })
                .orElseThrow(() -> new EntityNotFoundException("Client not found with ID: " + id));
    }

    public void deleteClient(Long id) {
        if (!clientRepository.existsById(id)) throw new EntityNotFoundException("Client not found with ID: " + id);
        clientRepository.deleteById(id);
    }
}
