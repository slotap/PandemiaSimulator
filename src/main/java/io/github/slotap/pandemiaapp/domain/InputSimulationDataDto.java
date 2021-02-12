package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class InputSimulationDataDto {
    private Long id;
    private String title;
    private int population;
    private int infected;
    private Double rFactor;
    private Double mortalityIndex;
    private int daysToHeal;
    private int daysToDie;
    private int daysToSimulate;
    private List<OutputSimulationData> outputData;
}
