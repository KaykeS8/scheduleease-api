package simao.project.agendamento.services;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import simao.project.agendamento.dtos.serviceItem.ServiceItemRequestDto;
import simao.project.agendamento.dtos.serviceItem.ServiceItemResponseDto;
import simao.project.agendamento.dtos.serviceItem.ServiceItemUpdateDto;
import simao.project.agendamento.entitys.ServiceItem;
import simao.project.agendamento.exceptions.EntityNotFoundException;
import simao.project.agendamento.mappers.ServiceItemMapper;
import simao.project.agendamento.repositories.ServiceItemRepository;

import java.util.List;

@Service
public class ServiceItemService {

    private final ServiceItemRepository serviceItemRepository;
    private final Logger log = LoggerFactory.getLogger(ServiceItemService.class);
    private final ServiceItemMapper mapper;

    public ServiceItemService(ServiceItemRepository serviceItemRepository, ServiceItemMapper mapper) {
        this.serviceItemRepository = serviceItemRepository;
        this.mapper = mapper;
    }

    public List<ServiceItemResponseDto> getAllServiceItem() {
        log.info("Get all service Item");
        return serviceItemRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Transactional
    public ServiceItemResponseDto createServiceItem(ServiceItemRequestDto serviceItemRequestDto) {
        log.info("Creating service item");
       ServiceItem serviceItemEntity = serviceItemRepository.save(mapper.toEntity(serviceItemRequestDto));
       return mapper.toDto(serviceItemEntity);
    }

    public ServiceItemResponseDto getServiceItemById(Long id) {
        log.info("Get service item by ID: {}", id);
        return serviceItemRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new EntityNotFoundException("Service item not found with ID: " + id));
    }

    @Transactional
    public ServiceItemResponseDto updateServiceItem(ServiceItemUpdateDto serviceItemUpdateDto, Long id) {
        log.info("Update service item with ID {}", id);
        return serviceItemRepository.findById(id)
                .map(service -> {
                    if (serviceItemUpdateDto.name() != null) service.setName(serviceItemUpdateDto.name());
                    if (serviceItemUpdateDto.description() != null) service.setDescription(serviceItemUpdateDto.description());
                    if (serviceItemUpdateDto.durationMinutes() != null) service.setDurationMinutes(serviceItemUpdateDto.durationMinutes());
                    if (serviceItemUpdateDto.price() != null) service.setPrice(serviceItemUpdateDto.price());
                    return mapper.toDto(serviceItemRepository.save(service));
                }).orElseThrow(() -> new EntityNotFoundException("Service item not found with ID: " + id));
    }


    public void deleteServiceItem(Long id) {
        if (!serviceItemRepository.existsById(id)) throw new EntityNotFoundException("Service item not found with ID: " + id);
        serviceItemRepository.deleteById(id);
    }
}
