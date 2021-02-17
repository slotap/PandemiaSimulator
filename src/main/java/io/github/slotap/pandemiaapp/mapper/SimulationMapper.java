package io.github.slotap.pandemiaapp.mapper;

import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationDataDto;
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
                outputData.getRFactor(),
                outputData.getMortalityIndex(),
                outputData.getDaysToHeal(),
                outputData.getDaysToDie(),
                outputData.getDaysToSimulate(),
                outputData.getOutputData()
        );
    }
    public OutputSimulationData mapToOutput (OutputSimulationDataDto outputSimulationDataDto){
        return new OutputSimulationData(
                outputSimulationDataDto.getId(),
                outputSimulationDataDto.getTitle(),
                outputSimulationDataDto.getPopulation(),
                outputSimulationDataDto.getInfected(),
                outputSimulationDataDto.getRFactor(),
                outputSimulationDataDto.getMortalityIndex(),
                outputSimulationDataDto.getDaysToHeal(),
                outputSimulationDataDto.getDaysToDie(),
                outputSimulationDataDto.getDaysToSimulate(),
                outputSimulationDataDto.getOutputData()
        );
    }

    public List<OutputSimulationDataDto> mapToSimulationList(List<OutputSimulationData> simData) {
        return simData.stream()
                .map(this::mapToOutputDto)
                .collect(Collectors.toList());
    }
}
