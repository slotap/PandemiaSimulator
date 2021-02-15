package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.repository.OutputSimulationDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DbService {
    private final OutputSimulationDataRepository outputSimulationRepository;

    public OutputSimulationData saveSimulationData (final OutputSimulationData outputSimulationData){
        return outputSimulationRepository.save(outputSimulationData);
    }

    public List<OutputSimulationData> getAllSimulations() {
        return outputSimulationRepository.findAll();
    }

    public Optional<OutputSimulationData> findById(Long id){
        return outputSimulationRepository.findById(id);
    }

    public void deleteSimulation(Long id){
        outputSimulationRepository.deleteById(id);
    }

    public boolean existById(long id) {
        return outputSimulationRepository.existsById(id);
    }
}
