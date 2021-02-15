package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;

public interface SimulationService {
    OutputSimulationData processSimulation(InputSimulationData inputData);
}
