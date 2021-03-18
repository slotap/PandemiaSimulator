package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedSimulationDataDto that = (ProcessedSimulationDataDto) o;
        return infectedTotal == that.infectedTotal && infectedDaily == that.infectedDaily && diedDaily == that.diedDaily && notInfectedDaily == that.notInfectedDaily && healedDaily == that.healedDaily && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infectedTotal, infectedDaily, diedDaily, notInfectedDaily, healedDaily);
    }
}
