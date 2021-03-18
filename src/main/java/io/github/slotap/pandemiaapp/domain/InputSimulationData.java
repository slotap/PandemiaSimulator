package io.github.slotap.pandemiaapp.domain;

import io.github.slotap.pandemiaapp.validator.ValidInputSimulationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
@ValidInputSimulationData
public class InputSimulationData {
    @NotNull
    private String title;
    @NotNull
    private int population;
    @NotNull
    private int infected;
    @NotNull
    private Double reproductionNumber;
    @NotNull
    private Double mortalityIndex;
    @NotNull
    private int daysToHeal;
    @NotNull
    private int daysToDie;
    @NotNull
    private int daysToSimulate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InputSimulationData that = (InputSimulationData) o;
        return population == that.population && infected == that.infected && daysToHeal == that.daysToHeal && daysToDie == that.daysToDie && daysToSimulate == that.daysToSimulate && title.equals(that.title) && Objects.equals(reproductionNumber, that.reproductionNumber) && Objects.equals(mortalityIndex, that.mortalityIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, population, infected, reproductionNumber, mortalityIndex, daysToHeal, daysToDie, daysToSimulate);
    }
}
