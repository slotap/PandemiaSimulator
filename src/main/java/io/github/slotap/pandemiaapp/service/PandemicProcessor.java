package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PandemicProcessor implements SimulationService{
    @Override
    public List<ProcessedSimulationData> processSimulation(InputSimulationData inputData) {
        return null;
    }
}
