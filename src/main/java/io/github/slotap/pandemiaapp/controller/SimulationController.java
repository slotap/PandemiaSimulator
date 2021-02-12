package io.github.slotap.pandemiaapp.controller;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.InputSimulationDataDto;
import io.github.slotap.pandemiaapp.mapper.SimulationMapper;
import io.github.slotap.pandemiaapp.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimulationController {
    private DbService dbService;
    private SimulationMapper simulationMapper;

    @GetMapping(value = "simulations")
    public List<InputSimulationDataDto> getSimulations() {
        List<InputSimulationData> simData = dbService.getAllSimulations();
        return simulationMapper.mapToSimulationList(simData);
    }



}
