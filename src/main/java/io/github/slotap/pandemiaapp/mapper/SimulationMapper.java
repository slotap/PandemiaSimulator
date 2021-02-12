package io.github.slotap.pandemiaapp.mapper;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.InputSimulationDataDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulationMapper {

    public InputSimulationDataDto mapToInputDto (InputSimulationData inputData){
        return new InputSimulationDataDto(
                inputData.getId(),
                inputData.getTitle(),
                inputData.getPopulation(),
                inputData.getInfected(),
                inputData.getRFactor(),
                inputData.getMortalityIndex(),
                inputData.getDaysToHeal(),
                inputData.getDaysToDie(),
                inputData.getDaysToSimulate(),
                inputData.getOutputData()
        );
    }
    public InputSimulationData mapToInput (InputSimulationDataDto inputData){
        return new InputSimulationData(
                inputData.getId(),
                inputData.getTitle(),
                inputData.getPopulation(),
                inputData.getInfected(),
                inputData.getRFactor(),
                inputData.getMortalityIndex(),
                inputData.getDaysToHeal(),
                inputData.getDaysToDie(),
                inputData.getDaysToSimulate(),
                inputData.getOutputData()
        );
    }

    public List<InputSimulationDataDto> mapToSimulationList(List<InputSimulationData> simData) {
        return simData.stream()
                .map(this::mapToInputDto)
                .collect(Collectors.toList());
    }
}
