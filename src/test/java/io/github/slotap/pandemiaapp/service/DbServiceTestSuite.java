package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import io.github.slotap.pandemiaapp.repository.OutputSimulationDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private OutputSimulationDataRepository outputSimulationDataRepository;

    @Test
    void testInputDataSaveWithOutputData(){
        //Given
        ProcessedSimulationData firstDayOfSimulation = new ProcessedSimulationData(300,30,70,200);
        ProcessedSimulationData secondDayOfSimulation = new ProcessedSimulationData(300,30,70,200);
        OutputSimulationData testSimulation = new OutputSimulationData("testSim",300,1,2.0,1.2,12,5,180);
        testSimulation.getOutputData().add(firstDayOfSimulation);
        testSimulation.getOutputData().add(secondDayOfSimulation);
        firstDayOfSimulation.setOutputSimulationData(testSimulation);
        secondDayOfSimulation.setOutputSimulationData(testSimulation);

        //When
        outputSimulationDataRepository.save(testSimulation);
        Long id = testSimulation.getId();

        //Then
        assertNotEquals(0 ,id);

        //CleanUp
        outputSimulationDataRepository.deleteById(id);
    }
}
