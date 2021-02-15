package io.github.slotap.pandemiaapp.controller;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationDataDto;
import io.github.slotap.pandemiaapp.mapper.SimulationMapper;
import io.github.slotap.pandemiaapp.service.DbService;
import io.github.slotap.pandemiaapp.service.SimulationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimulationController {
    private DbService dbService;
    private SimulationMapper simulationMapper;
    private SimulationService simulationService;

    @GetMapping(value = "/simulations")
    public ResponseEntity<List<OutputSimulationDataDto>> getAllSimulations() {
        List<OutputSimulationData> simData = dbService.getAllSimulations();
        return ResponseEntity.ok(simulationMapper.mapToSimulationList(simData));
    }

    @GetMapping(value = "/simulations/{simId}")
    public OutputSimulationDataDto getSimulation(@PathVariable Long simId) throws SimulationNotFoundException {
        return simulationMapper.mapToOutputDto(dbService.findById(simId).orElseThrow(SimulationNotFoundException::new));
    }

    @DeleteMapping(value = "/simulations/{simId}")
    public void deleteSimulation (@PathVariable Long simId) {
        dbService.deleteSimulation(simId);
    }

    @PostMapping(value = "/simulations",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputSimulationDataDto> createSimulation(@RequestBody InputSimulationData inputData) {
        OutputSimulationData processedSimulation = simulationService.processSimulation(inputData);
        dbService.saveSimulationData(processedSimulation);
        return ResponseEntity.ok(simulationMapper.mapToOutputDto(processedSimulation));
    }

/*    @PutMapping(value = "/simulations/{simIs}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public InputSimulationDataDto updateSimulation(@RequestBody InputSimulationDataDto inputDto,@PathVariable Long simId ) {
        InputSimulationData processedSimulation = simulationService.processSimulation(simulationMapper.mapToInput(inputDto));
        dbService.updateSimulationData(simId, processedSimulation);
        return simulationMapper.mapToInputDto(processedSimulation);
    }*/



}
