package simao.project.agendamento.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simao.project.agendamento.dtos.client.ClientRequestDto;
import simao.project.agendamento.dtos.client.ClientResponseDto;
import simao.project.agendamento.dtos.client.ClientUpdateDto;
import simao.project.agendamento.services.ClientService;

import java.util.List;

@RestController
@RequestMapping("api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDto>> getAllClients() {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getAllClients());
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> createClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.getClientById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDto> updateClient(@RequestBody @Valid ClientUpdateDto clientUpdateDto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(clientService.updateClient(clientUpdateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
