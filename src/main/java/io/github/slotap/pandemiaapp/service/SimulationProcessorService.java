package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.controller.SimulationNotFoundException;
import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
@Getter
public class SimulationProcessorService {
    private final SimulationService simulationService;
    private final DbService dbService;

    public OutputSimulationData createOutputData(InputSimulationData inputData){
            OutputSimulationData createdOutput = new OutputSimulationData();
            createdOutput.updateSimData(inputData);
            createdOutput.setOutputData(simulationService.processSimulation(inputData));
        return  createdOutput;
    }

    public OutputSimulationData updateOutputData(Long id, InputSimulationData inputData) throws SimulationNotFoundException{
            OutputSimulationData createdOutput = dbService.findById(id).orElseThrow(SimulationNotFoundException::new);
            createdOutput.updateSimData(inputData);
            createdOutput.setOutputData(simulationService.processSimulation(inputData));
        return  createdOutput;
    }
}
