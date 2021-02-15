package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "processed_data")
public class ProcessedSimulationData {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name="id",unique = true)
    private Long id;

    @Column(name = "infected_daily")
    @NotNull
    private int infectedDaily;

    @Column(name = "died_daily")
    @NotNull
    private int diedDaily;

    @Column(name = "not_infecte_daily")
    @NotNull
    private int notInfectedDaily;

    @Column(name = "healed_daily")
    @NotNull
    private int healedDaily;

    @ManyToOne
    @JoinColumn(name = "output_data")
    private OutputSimulationData outputSimulationData;

    public ProcessedSimulationData(int infectedDaily, int diedDaily, int notInfectedDaily, int healedDaily){
        this.infectedDaily=infectedDaily;
        this.diedDaily=diedDaily;
        this.notInfectedDaily=notInfectedDaily;
        this.healedDaily=healedDaily;
    }

    public void setOutputSimulationData(OutputSimulationData outputSimulationData) {
        this.outputSimulationData = outputSimulationData;
    }
}
