package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "output_data")
public class OutputSimulationData {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id",unique = true)
    private Long id;

    @Column(name = "name")
    @NotNull
    private String title;

    @Column(name = "population")
    @NotNull
    private int population;

    @Column(name = "infected")
    @NotNull
    private int infected;

    @Column(name = "r_factor")
    @NotNull
    private Double rFactor;

    @Column(name = "mortality")
    @NotNull
    private Double mortalityIndex;

    @Column(name = "heal_days")
    @NotNull
    private int daysToHeal;

    @Column(name = "to_die_days")
    @NotNull
    private int daysToDie;

    @Column(name = "sim_days")
    @NotNull
    private int daysToSimulate;

    @OneToMany(
            targetEntity = ProcessedSimulationData.class,
            mappedBy = "outputSimulationData",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<ProcessedSimulationData> outputData = new ArrayList<>();

    public OutputSimulationData(@NotNull String title, @NotNull int population, @NotNull int infected, @NotNull Double rFactor, @NotNull Double mortalityIndex, @NotNull int daysToHeal, @NotNull int daysToDie, @NotNull int daysToSimulate) {
        this.title = title;
        this.population = population;
        this.infected = infected;
        this.rFactor = rFactor;
        this.mortalityIndex = mortalityIndex;
        this.daysToHeal = daysToHeal;
        this.daysToDie = daysToDie;
        this.daysToSimulate = daysToSimulate;
    }

    public void setOutputData(List<ProcessedSimulationData> outputData) {
        this.outputData = outputData;
    }
}
