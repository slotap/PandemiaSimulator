package io.github.slotap.pandemiaapp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Column(name = "infected_total")
    @NotNull
    private int infectedTotal;

    @Column(name = "infected_daily")
    @NotNull
    private int infectedDaily;

    @Column(name = "died_daily")
    @NotNull
    private int diedDaily;

    @Column(name = "not_infected_daily")
    @NotNull
    private int notInfectedDaily;

    @Column(name = "healed_daily")
    @NotNull
    private int healedDaily;

    @ManyToOne
    @JoinColumn(name = "output_data_id")
    private OutputSimulationData outputSimulationData;

    public ProcessedSimulationData(int infectedTotal, int diedDaily, int notInfectedDaily, int healedDaily){
        this.infectedTotal =infectedTotal;
        this.diedDaily=diedDaily;
        this.notInfectedDaily=notInfectedDaily;
        this.healedDaily=healedDaily;
    }

    public ProcessedSimulationData(int infectedTotal, int diedDaily, int healedDaily, int infectedDaily, int notInfectedDaily){
        this.infectedTotal =infectedTotal;
        this.diedDaily=diedDaily;
        this.notInfectedDaily=notInfectedDaily;
        this.healedDaily=healedDaily;
        this.infectedDaily=infectedDaily;
    }

    public void setOutputSimulationData(OutputSimulationData outputSimulationData) {
        this.outputSimulationData = outputSimulationData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedSimulationData that = (ProcessedSimulationData) o;
        return infectedTotal == that.infectedTotal && infectedDaily == that.infectedDaily && diedDaily == that.diedDaily && notInfectedDaily == that.notInfectedDaily && healedDaily == that.healedDaily && Objects.equals(id, that.id) && Objects.equals(outputSimulationData, that.outputSimulationData);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, infectedTotal, infectedDaily, diedDaily, notInfectedDaily, healedDaily, outputSimulationData);
    }
}
