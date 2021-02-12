package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.repository.InputSimulationRepository;
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
    private InputSimulationRepository inputSimulationRepository;

    @Test
    void testInputDataSaveWithOutputData(){
        //Given
        OutputSimulationData firstDayOfSimulation = new OutputSimulationData(300,30,70,200);
        OutputSimulationData secondDayOfSimulation = new OutputSimulationData(300,30,70,200);
        InputSimulationData testSimulation = new InputSimulationData("testSim",300,1,2.0,1.2,12,5,180);
        testSimulation.getOutputData().add(firstDayOfSimulation);
        testSimulation.getOutputData().add(secondDayOfSimulation);
        firstDayOfSimulation.setInputSimulationData(testSimulation);
        secondDayOfSimulation.setInputSimulationData(testSimulation);

        //When
        inputSimulationRepository.save(testSimulation);
        Long id = testSimulation.getId();

        //Then
        assertNotEquals(0 ,id);

        //CleanUp
        inputSimulationRepository.deleteById(id);
    }
}
