package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PandemicProcessorTest {

    private final SimulationService processor = new PandemicProcessor();

    @Test
    void shouldReturnListOfProcessedDataWithTenDaysOfSimulation() {
        //Given
        InputSimulationData inputData = new InputSimulationData("testSim",100,1,2.0,0.5,4,3,10);

        //When
        List<ProcessedSimulationData> resultList = processor.processSimulation(inputData);

        //Then
        assertEquals(10, resultList.size());
        assertEquals(100,(resultList.get(9).getDiedDaily()+resultList.get(9).getHealedDaily()+resultList.get(9).getInfectedTotal()+resultList.get(9).getNotInfectedDaily()));
        assertEquals(49, resultList.get(9).getDiedDaily());
        assertEquals(51, resultList.get(9).getHealedDaily());
        assertEquals(0, resultList.get(9).getInfectedTotal());
        assertEquals(0, resultList.get(9).getNotInfectedDaily());
        assertEquals(0, resultList.get(9).getInfectedDaily());
    }

    @Test
    void shouldReturnListOfProcessedDataWhenInfectedReachFullPopulation() {
        //Given
        InputSimulationData inputData = new InputSimulationData("testSim",1000,10,2.0,0.5,10,5,10);

        //When
        List<ProcessedSimulationData> resultList = processor.processSimulation(inputData);

        //Then
        assertEquals(10, resultList.size());
        assertEquals(1000,(resultList.get(9).getDiedDaily()+resultList.get(9).getHealedDaily()+resultList.get(9).getInfectedTotal()+resultList.get(9).getNotInfectedDaily()));
    }

    @Test
    void shouldReturnListOfProcessedDataWhenThousandDaysOfSimulation() {
        //Given
        InputSimulationData inputData = new InputSimulationData("testSim",10000,10,2.0,0.5,10,15,1000);

        //When
        List<ProcessedSimulationData> resultList = processor.processSimulation(inputData);

        //Then
        assertEquals(1000, resultList.size());
        assertEquals(10000,(resultList.get(9).getDiedDaily()+resultList.get(9).getHealedDaily()+resultList.get(9).getInfectedTotal()+resultList.get(9).getNotInfectedDaily()));
    }

}