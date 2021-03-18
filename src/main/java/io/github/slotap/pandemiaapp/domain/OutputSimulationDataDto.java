package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class OutputSimulationDataDto {
    private Long id;
    private String title;
    private int population;
    private int infected;
    private Double reproductionNumber;
    private Double mortalityIndex;
    private int daysToHeal;
    private int daysToDie;
    private int daysToSimulate;
    private List<ProcessedSimulationDataDto> outputData;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputSimulationDataDto that = (OutputSimulationDataDto) o;
        return population == that.population && infected == that.infected && daysToHeal == that.daysToHeal && daysToDie == that.daysToDie && daysToSimulate == that.daysToSimulate && Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(reproductionNumber, that.reproductionNumber) && Objects.equals(mortalityIndex, that.mortalityIndex) && Objects.equals(outputData, that.outputData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, population, infected, reproductionNumber, mortalityIndex, daysToHeal, daysToDie, daysToSimulate, outputData);
    }
}
