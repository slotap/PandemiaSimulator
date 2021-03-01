package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProcessedSimulationDataDto {
    private Long id;
    private int infectedTotal;
    private int infectedDaily;
    private int diedDaily;
    private int notInfectedDaily;
    private int healedDaily;

    public ProcessedSimulationDataDto(int infectedTotal, int diedDaily, int notInfectedDaily, int healedDaily){
        this.infectedTotal =infectedTotal;
        this.diedDaily=diedDaily;
        this.notInfectedDaily=notInfectedDaily;
        this.healedDaily=healedDaily;
    }
}
