package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.repository.InputSimulationRepository;
import io.github.slotap.pandemiaapp.repository.OutputSimulationDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final InputSimulationRepository inputRepository;
    private final OutputSimulationDataRepository outputRepository;

    public InputSimulationData saveSimulationData (final InputSimulationData inputSimulationData){
        return inputRepository.save(inputSimulationData);
    }

    public List<InputSimulationData> getAllSimulations() {
        return inputRepository.findAll();
    }

    public Optional<InputSimulationData> findById(Long id){
        return inputRepository.findById(id);
    }
}
