package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PandemicProcessorTest {

    @Test
    void shouldReturnListOfProcessedDataWithTenDaysOfSimulation() {
        //Given
        InputSimulationData inputData = new InputSimulationData("testSim",10000,10,2.0,0.5,10,5,10);
        PandemicProcessor processor = new PandemicProcessor();

        //When
        List<ProcessedSimulationData> resultList = processor.processSimulation(inputData);

        //Then
        assertEquals(10, resultList.size());
        assertEquals(10000,(resultList.get(9).getDiedDaily()+resultList.get(9).getHealedDaily()+resultList.get(9).getInfectedTotal()+resultList.get(9).getNotInfectedDaily()));
    }


}