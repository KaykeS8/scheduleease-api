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
import simao.project.agendamento.dtos.client.ClientUpdateDto;
import simao.project.agendamento.entitys.Client;
import simao.project.agendamento.mappers.ClientMapper;
import simao.project.agendamento.repositories.ClientRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        client = new Client("test", "test@gmail.com", "123456");
        client.setId(1L);
        client.setSchedulings(null);
        client.setCreatedAt(LocalDateTime.now());

        request = new ClientRequestDto("test", "client test", "123456");
        response = new ClientResponseDto(1L, "test", "test@gmail.com", "123456");
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

    @Nested
    @DisplayName("createClient()")
    class CreateClient {

        @Test
        @DisplayName("Should create one client when all args is correctly")
        void shouldCreateClient() {
            //arrange
            when(mapper.toEntity(request)).thenReturn(client);
            when(mapper.toDto(client)).thenReturn(response);
            when(clientRepository.save(any(Client.class))).thenReturn(client);

            //act
            ClientResponseDto clientResonse = clientService.createClient(request);

            //assert
            assertThat(clientResonse).isNotNull();
            assertThat(clientResonse.id()).isEqualTo(1L);
            assertThat(clientResonse.name()).isEqualTo("test");
            assertThat(clientResonse.email()).isEqualTo("test@gmail.com");
            assertThat(clientResonse.phone()).isEqualTo("123456");

            //verify
            verify(clientRepository, times(1)).save(any(Client.class));
        }
    }

    @Nested
    @DisplayName("getClientById()")
    class GetClientById {
        @Test
        @DisplayName("Should return client when id exist")
        void shouldReturnClientById() {
            //arrange
            when(clientRepository.findById(1L)).thenReturn(Optional.of(client));
            when(mapper.toDto(client)).thenReturn(response);

            //ACT
            ClientResponseDto clientResponse = clientService.getClientById(1L);

            //assert
            assertThat(clientResponse).isNotNull();
            assertThat(clientResponse.id()).isEqualTo(1L);
            assertThat(clientResponse.name()).isEqualTo("test");

        }
    }

    @Nested
    @DisplayName("updateClient()")
    class UpdateClient {

        @Test
        @DisplayName("Should update client when ID exists")
        void shouldUpdateClient() {
            // Arrange
            ClientUpdateDto updateDto = new ClientUpdateDto(
                    "Updated Name",
                    "updated@gmail.com",
                    "999999999"
            );

            Client updatedClient = new Client();
            updatedClient.setId(1L);
            updatedClient.setName("Updated Name");
            updatedClient.setEmail("updated@gmail.com");
            updatedClient.setPhone("999999999");

            ClientResponseDto responseDto = new ClientResponseDto(
                    1L,
                    "Updated Name",
                    "updated@gmail.com",
                    "999999999"
            );

            when(clientRepository.findById(1L))
                    .thenReturn(Optional.of(client));

            when(clientRepository.save(any(Client.class)))
                    .thenReturn(updatedClient);

            when(mapper.toDto(updatedClient))
                    .thenReturn(responseDto);

            // Act
            ClientResponseDto response =
                    clientService.updateClient(updateDto, 1L);

            // Assert
            assertThat(response).isNotNull();
            assertThat(response.id()).isEqualTo(1L);
            assertThat(response.name()).isEqualTo("Updated Name");
            assertThat(response.email()).isEqualTo("updated@gmail.com");
            assertThat(response.phone()).isEqualTo("999999999");

            verify(clientRepository).findById(1L);
            verify(clientRepository).save(any(Client.class));
            verify(mapper).toDto(updatedClient);
        }
    }

    @Nested
    @DisplayName("deleteClient()")
    class DeleteClient {

        @Test
        @DisplayName("Should delete client by id when exist")
        void shouldDeleteClientById() {
            when(clientRepository.existsById(1L)).thenReturn(true);
            doNothing().when(clientRepository).deleteById(1L);

            clientService.deleteClient(1L);

            verify(clientRepository, times(1)).deleteById(1L);
        }
    }


}