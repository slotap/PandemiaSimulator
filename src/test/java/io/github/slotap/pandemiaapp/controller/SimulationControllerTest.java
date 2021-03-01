package io.github.slotap.pandemiaapp.controller;

import com.google.gson.Gson;
import io.github.slotap.pandemiaapp.domain.*;
import io.github.slotap.pandemiaapp.mapper.SimulationMapper;
import io.github.slotap.pandemiaapp.service.DbService;
import io.github.slotap.pandemiaapp.service.SimulationProcessorService;
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
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
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
    private SimulationProcessorService simulationService;

    @Test
    void shouldFetchAllSimulations() throws Exception {
        //Given
        List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50, 20, 20, 10));
        List<ProcessedSimulationDataDto> processedDataDto = List.of(new ProcessedSimulationDataDto(50, 20, 20, 10));
        List<OutputSimulationData> outputSimulationDataList = List.of(new OutputSimulationData(1L, "testSim", 100, 20, 1.5, 1.5, 12, 5, 100, processedData));
        List<OutputSimulationDataDto> outputSimulationDataListDto = List.of(new OutputSimulationDataDto(1L, "testSim", 100, 20, 1.5, 1.5, 12, 5, 100, processedDataDto));
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
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].rfactor", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mortalityIndex", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToHeal", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToDie", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].daysToSimulate", Matchers.is(100)))
                //Processed Data List
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].infectedTotal", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].diedDaily", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].notInfectedDaily", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].outputData[0].healedDaily", Matchers.is(10)));
    }

    @Test
    void shouldFetchOneSimulation() throws Exception {
        //Given
        List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50, 20, 20, 10));
        List<ProcessedSimulationDataDto> processedDataDto = List.of(new ProcessedSimulationDataDto(50, 20, 20, 10));
        Optional<OutputSimulationData> outputSimulationData = Optional.of(new OutputSimulationData(1L, "testSim", 100, 20, 1.5, 1.5, 12, 5, 100, processedData));
        OutputSimulationDataDto outputSimulationDataDto = new OutputSimulationDataDto(1L, "testSim", 100, 20, 1.5, 1.5, 12, 5, 100, processedDataDto);

        when(dbService.findById(1L)).thenReturn(outputSimulationData);
        when(simulationMapper.mapToOutputDto(outputSimulationData.get())).thenReturn(outputSimulationDataDto);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/simulations/1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testSim")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.population", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.infected", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rfactor", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mortalityIndex", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToHeal", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToDie", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToSimulate", Matchers.is(100)))
                //Processed Data List
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputData[0].infectedTotal", Matchers.is(50)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputData[0].diedDaily", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputData[0].notInfectedDaily", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.outputData[0].healedDaily", Matchers.is(10)));
    }


    @Test
    void shouldCreateSimulation() throws Exception {
        //Given
        List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50,20,20,10));
        List<ProcessedSimulationDataDto> processedDataDto = List.of(new ProcessedSimulationDataDto(50,20,20,10));
        OutputSimulationData outputSimulationData = new OutputSimulationData(1L,"testSim",100,20,1.5,0.5,12,5,100,processedData);
        OutputSimulationDataDto outputSimulationDataDto = new OutputSimulationDataDto(1L,"testSim",100,20,1.5,0.5,12,5,100,processedDataDto);
        InputSimulationData inputSimulationData = new InputSimulationData ("testSim",100,1,1.5,0.5,4,3,10);

        when(simulationService.createOutputData(any(InputSimulationData.class))).thenReturn(outputSimulationData);
        when(dbService.saveSimulationData(outputSimulationData)).thenReturn(outputSimulationData);
        when(simulationMapper.mapToOutputDto(outputSimulationData)).thenReturn(outputSimulationDataDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(inputSimulationData);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testSim")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.population", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.infected", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rfactor", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mortalityIndex", Matchers.is(0.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToHeal", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToDie", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToSimulate", Matchers.is(100)));

    }

    @Test
    void shouldUpdateSimulation() throws Exception {
        //Given
        List<ProcessedSimulationData> processedData = List.of(new ProcessedSimulationData(50,20,20,10));
        List<ProcessedSimulationDataDto> processedDataDto = List.of(new ProcessedSimulationDataDto(50,20,20,10));
        OutputSimulationData outputSimulationData = new OutputSimulationData(1L,"testSim",100,20,1.5,0.5,12,5,100,processedData);
        OutputSimulationDataDto outputSimulationDataDto = new OutputSimulationDataDto(1L,"testSim",100,20,1.5,0.5,12,5,100,processedDataDto);
        InputSimulationData inputSimulationData = new InputSimulationData ("testSim",100,20,1.5,0.5,12,5,100);

        when(simulationService.updateOutputData(any(Long.class),any(InputSimulationData.class))).thenReturn(outputSimulationData);
        when(dbService.saveSimulationData(outputSimulationData)).thenReturn(outputSimulationData);
        when(simulationMapper.mapToOutputDto(outputSimulationData)).thenReturn(outputSimulationDataDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(inputSimulationData);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put("/api/simulations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.is("testSim")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.population", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.infected", Matchers.is(20)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rfactor", Matchers.is(1.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mortalityIndex", Matchers.is(0.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToHeal", Matchers.is(12)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToDie", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.daysToSimulate", Matchers.is(100)));

    }
}