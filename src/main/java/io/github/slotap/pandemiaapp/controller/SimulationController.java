package io.github.slotap.pandemiaapp.controller;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationDataDto;
import io.github.slotap.pandemiaapp.mapper.SimulationMapper;
import io.github.slotap.pandemiaapp.service.DbService;
import io.github.slotap.pandemiaapp.service.SimulationProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimulationController {
    private final DbService dbService;
    private final SimulationMapper simulationMapper;
    private final SimulationProcessorService simulationProcessor;

    @GetMapping(value = "/simulations")
    public ResponseEntity<List<OutputSimulationDataDto>> getAllSimulations() {
        List<OutputSimulationData> simData = dbService.getAllSimulations();
        return ResponseEntity.ok(simulationMapper.mapToSimulationList(simData));
    }

    @GetMapping(value = "/simulations/{simId}")
    public ResponseEntity<OutputSimulationDataDto> getSimulation(@PathVariable Long simId) throws SimulationNotFoundException {
        return ResponseEntity.ok(simulationMapper.mapToOutputDto(dbService.findById(simId).orElseThrow(() -> new SimulationNotFoundException("Simulation Not Found"))));
    }

    @DeleteMapping(value = "/simulations/{simId}")
    public void deleteSimulation (@PathVariable Long simId) {
        dbService.deleteSimulation(simId);
    }

    @PostMapping(value = "/simulations",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputSimulationDataDto> createSimulation(@RequestBody @Valid InputSimulationData inputData) {
        OutputSimulationData processedSimulation = simulationProcessor.createOutputData(inputData);
        processedSimulation = dbService.saveSimulationData(processedSimulation);
        return ResponseEntity.ok(simulationMapper.mapToOutputDto(processedSimulation));
    }

    @PutMapping(value = "/simulations/{simId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OutputSimulationDataDto> updateSimulation(@PathVariable Long simId,@RequestBody  @Valid InputSimulationData inputData) {
            OutputSimulationData processedSimulation;
            try {
                 processedSimulation = simulationProcessor.updateOutputData(simId,inputData);
            } catch (SimulationNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        return ResponseEntity.ok(simulationMapper.mapToOutputDto(processedSimulation));
    }
    @PutMapping(value = "/save/{simId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUpdatedSimulation(@PathVariable Long simId,@RequestBody @Valid InputSimulationData inputData){
        try {
              dbService.saveSimulationData(simulationProcessor.updateOutputData(simId,inputData));
        } catch (SimulationNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
