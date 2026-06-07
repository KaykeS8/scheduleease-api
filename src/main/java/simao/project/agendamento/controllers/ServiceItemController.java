package simao.project.agendamento.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simao.project.agendamento.dtos.serviceItem.ServiceItemRequestDto;
import simao.project.agendamento.dtos.serviceItem.ServiceItemResponseDto;
import simao.project.agendamento.dtos.serviceItem.ServiceItemUpdateDto;
import simao.project.agendamento.entitys.ServiceItem;
import simao.project.agendamento.services.ServiceItemService;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceItemController {
    private final ServiceItemService serviceItemService;

    public ServiceItemController(ServiceItemService serviceItemService) {
        this.serviceItemService = serviceItemService;
    }

    @GetMapping
    public ResponseEntity<List<ServiceItemResponseDto>> getAllServiceItem() {
        return ResponseEntity.status(HttpStatus.OK).body(serviceItemService.getAllServiceItem());
    }

    @PostMapping
    public ResponseEntity<ServiceItemResponseDto> createServiceItem(@RequestBody @Valid ServiceItemRequestDto serviceItemRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceItemService.createServiceItem(serviceItemRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceItemResponseDto> getServiceItemById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceItemService.getServiceItemById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceItemResponseDto> updateServiceItem(@RequestBody ServiceItemUpdateDto serviceItemUpdateDto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(serviceItemService.updateServiceItem(serviceItemUpdateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceItem(@PathVariable Long id) {
        serviceItemService.deleteServiceItem(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
