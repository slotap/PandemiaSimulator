package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.controller.SimulationNotFoundException;
import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SimulationProcessorServiceTest {

    @Mock
    private SimulationService simulationService;

    @Mock
    private DbService dbService;

    @InjectMocks
    private SimulationProcessorService processorService;

    private final List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50,20,20,10));
    private final InputSimulationData inputTestData = createTestData();


    @Test
    void shouldCreateOutputData() {
        //Given
        when(simulationService.processSimulation(inputTestData)).thenReturn(processedData);

        //When
        OutputSimulationData testData = processorService.createOutputData(inputTestData);

        //Then
        assertThat(testData).extracting("title","population","infected","rFactor","mortalityIndex","daysToHeal","daysToDie","daysToSimulate")
                            .contains("testSim",100,20,1.5,1.5,12,5,100);
        assertThat(testData.getOutputData()).hasSize(1);
    }

    @Test
    void shouldUpdateOutputData() throws SimulationNotFoundException {
        //Given
        Optional<OutputSimulationData> foundTestOutputData = Optional.of(createExpectedData());

        when(dbService.findById(1L)).thenReturn(foundTestOutputData);
        when(simulationService.processSimulation(inputTestData)).thenReturn(processedData);

        //When
        OutputSimulationData testData = processorService.updateOutputData(1L,inputTestData);

        //Then
        assertThat(testData).extracting("title","population","infected","rFactor","mortalityIndex","daysToHeal","daysToDie","daysToSimulate")
                .contains("testSim",100,20,1.5,1.5,12,5,100);
        assertThat(testData.getOutputData()).hasSize(1);
    }

    @Test
    void shouldThrowSimulationNotFoundException(){
        //Given
        when(dbService.findById(1L)).thenReturn(Optional.empty());

        //When
        Throwable exception = assertThrows(SimulationNotFoundException.class, () -> processorService.updateOutputData(1L,inputTestData));

        //Then
        assertThat(exception).hasMessage("Simulation Not Found");
    }

    private InputSimulationData createTestData(){
        return new InputSimulationData("testSim",100,20,1.5,1.5,12,5,100);
    }

    private OutputSimulationData createExpectedData() {
        return new OutputSimulationData(1L,"testSim",100,20,1.5,1.5,12,5,100,processedData);
    }
}