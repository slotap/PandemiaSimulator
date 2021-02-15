package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputSimulationData {
    private String title;
    private int population;
    private int infected;
    private Double rFactor;
    private Double mortalityIndex;
    private int daysToHeal;
    private int daysToDie;
    private int daysToSimulate;
}
