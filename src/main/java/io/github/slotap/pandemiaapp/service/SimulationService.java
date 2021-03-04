package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;

import java.util.List;

public interface SimulationService {
    List<ProcessedSimulationData> processSimulation(InputSimulationData inputData);
}
