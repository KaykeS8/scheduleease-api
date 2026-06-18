package simao.project.agendamento.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simao.project.agendamento.dtos.scheduling.SchedulingRequestDto;
import simao.project.agendamento.dtos.scheduling.SchedulingResponseDto;
import simao.project.agendamento.dtos.scheduling.SchedulingUpdateDto;
import simao.project.agendamento.services.SchedulingService;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class SchedulingController {
    private final SchedulingService schedulingService;

    public SchedulingController(SchedulingService schedulingService) {
        this.schedulingService = schedulingService;
    }

    @GetMapping
    public ResponseEntity<List<SchedulingResponseDto>> getAllScheduling() {
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.getAllScheduling());
    }

    @PostMapping
    public ResponseEntity<SchedulingResponseDto> createScheduling(@RequestBody @Valid SchedulingRequestDto schedulingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(schedulingService.createScheduling(schedulingRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchedulingResponseDto> getSchedulingById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.getSchedulingById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SchedulingResponseDto> updateScheduling(@RequestBody @Valid SchedulingUpdateDto schedulingUpdateDto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(schedulingService.updateScheduling(schedulingUpdateDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduling(@PathVariable Long id) {
        schedulingService.deleteScheduling(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

