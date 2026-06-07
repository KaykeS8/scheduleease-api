package simao.project.agendamento.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import simao.project.agendamento.dtos.client.ClientRequestDto;
import simao.project.agendamento.dtos.client.ClientResponseDto;
import simao.project.agendamento.entitys.Client;
import simao.project.agendamento.mappers.ClientMapper;
import simao.project.agendamento.repositories.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)

@DisplayName("Client service - test unit")
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ClientMapper mapper;

    @InjectMocks
    private ClientService clientService;

    private Client client;
    private ClientRequestDto request;
    private ClientResponseDto response;


    @BeforeEach
    void setup() {
        client = new Client("test", "client test", "123456");
        client.setId(1L);
        client.setSchedulings(null);
        client.setCreatedAt(LocalDateTime.now());

        request = new ClientRequestDto("test", "client test", "123456");
        response = new ClientResponseDto(1L, "test", "client test", "123456", null, LocalDateTime.now());
    }

    @Nested
    @DisplayName("getAllClients()")
    class getAllClients {

        @Test
        @DisplayName("Should return all clients if exist")
        void shouldReturnAllClients() {
            // arrange
            when(clientRepository.findAll()).thenReturn(List.of(client));
            when(mapper.toDto(client)).thenReturn(response);

            // act
            List<ClientResponseDto> clientResponse = clientService.getAllClients();

            // assert
            assertThat(clientResponse).isNotNull();
            assertThat(clientResponse).hasSize(1);
            assertThat(clientResponse.getFirst().name()).isEqualTo("test");

            verify(clientRepository).findAll();
            verify(mapper).toDto(client);
        }

        @Test
        @DisplayName("Should return empty list when not have clients")
        void shouldReturnEmptyList() {
            when(clientRepository.findAll()).thenReturn(List.of());

            List<ClientResponseDto> clientResponse = clientService.getAllClients();

            assertThat(clientResponse).hasSize(0);
            assertThat(clientResponse).isNotNull();
            assertThat(clientResponse).isEmpty();
        }
    }
}