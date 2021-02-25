package io.github.slotap.pandemiaapp.domain;

import io.github.slotap.pandemiaapp.Validator.ValidInputSimulationData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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
    private Double rFactor;
    @NotNull
    private Double mortalityIndex;
    @NotNull
    private int daysToHeal;
    @NotNull
    private int daysToDie;
    @NotNull
    private int daysToSimulate;
}
