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
@Entity(name = "input_data")
public class InputSimulationData {
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
            targetEntity = OutputSimulationData.class,
            mappedBy = "inputSimulationData",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    private List<OutputSimulationData> outputData = new ArrayList<>();
}
