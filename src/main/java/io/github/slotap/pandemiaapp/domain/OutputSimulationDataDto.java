package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

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
}
