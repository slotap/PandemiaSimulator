package io.github.slotap.pandemiaapp.controller;

import io.github.slotap.pandemiaapp.domain.OutputSimulationData;
import io.github.slotap.pandemiaapp.domain.OutputSimulationDataDto;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import io.github.slotap.pandemiaapp.mapper.SimulationMapper;
import io.github.slotap.pandemiaapp.service.DbService;
import io.github.slotap.pandemiaapp.service.SimulationService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringJUnitWebConfig
@WebMvcTest(SimulationController.class)
class SimulationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService dbService;

    @MockBean
    private SimulationMapper simulationMapper;

    @MockBean
    private SimulationService simulationService;

    @Test
    void shouldFetchAllSimulations() throws Exception{
        //Given
        List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50,20,20,10));
        List<OutputSimulationData> outputSimulationDataList = List.of(new OutputSimulationData(1L,"testSim",100,20,1.5,1.5,12,5,100,processedData));
        List<OutputSimulationDataDto> outputSimulationDataListDto = List.of(new OutputSimulationDataDto(1L,"testSim",100,20,1.5,1.5,12,5,100,processedData));
        when(dbService.getAllSimulations()).thenReturn(outputSimulationDataList);
        when(simulationMapper.mapToSimulationList(outputSimulationDataList)).thenReturn(outputSimulationDataListDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/simulations")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                            .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].title", Matchers.is("testSim")))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].population", Matchers.is(100)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].infected", Matchers.is(20)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].rFactor", Matchers.is(1.5)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].mortalityIndex", Matchers.is(1.5)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToHeal", Matchers.is(12)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToDie", Matchers.is(5)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToSimulate", Matchers.is(100)))
                            //Processed Data List
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].infectedDaily", Matchers.is(50)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].diedDaily", Matchers.is(20)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].notInfectedDaily", Matchers.is(20)))
                            .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].healedDaily", Matchers.is(10)));
    }

    @Test
    void shouldFetchOneSimulation() {
    }

    @Test
    void shouldDeleteSimulation() {
    }

    @Test
    void createSimulation() {
    }

    @Test
    void updateSimulation() {
    }
}