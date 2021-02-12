package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.repository.InputSimulationRepository;
import org.springframework.stereotype.Service;

@Service
public class DbService {
    private final InputSimulationRepository inputSimulationRepository;

    public DbService(InputSimulationRepository inputSimulationRepository) {
        this.inputSimulationRepository = inputSimulationRepository;
    }

    public InputSimulationData saveSimulationData (final InputSimulationData inputSimulationData){
        return inputSimulationRepository.save(inputSimulationData);
    }
}
