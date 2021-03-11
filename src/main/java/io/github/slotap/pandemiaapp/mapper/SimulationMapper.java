package io.github.slotap.pandemiaapp.mapper;

import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationDataDto;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationDataDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulationMapper {

    public OutputSimulationDataDto mapToOutputDto (OutputSimulationData outputData){
        return new OutputSimulationDataDto(
                outputData.getId(),
                outputData.getTitle(),
                outputData.getPopulation(),
                outputData.getInfected(),
                outputData.getReproductionNumber(),
                outputData.getMortalityIndex(),
                outputData.getDaysToHeal(),
                outputData.getDaysToDie(),
                outputData.getDaysToSimulate(),
                mapToListOfProcessedSimDataDto(outputData)
        );
    }

    private List<ProcessedSimulationDataDto> mapToListOfProcessedSimDataDto(OutputSimulationData outputData) {
       return outputData.getOutputData().stream()
                    .map(this::mapToProcessedSimDataDto)
                    .collect(Collectors.toList());

    }

    private ProcessedSimulationDataDto mapToProcessedSimDataDto(ProcessedSimulationData processedData) {
       return new ProcessedSimulationDataDto(
               processedData.getId(),
               processedData.getInfectedTotal(),
               processedData.getInfectedDaily(),
               processedData.getDiedDaily(),
               processedData.getNotInfectedDaily(),
               processedData.getHealedDaily()
       );
    }

    public List<OutputSimulationDataDto> mapToSimulationList(List<OutputSimulationData> simData) {
        return simData.stream()
                .map(this::mapToOutputDto)
                .collect(Collectors.toList());
    }
}
