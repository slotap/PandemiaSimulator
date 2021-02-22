package io.github.slotap.pandemiaapp.service;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;
import io.github.slotap.pandemiaapp.domain.ProcessedSimulationData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PandemicProcessor implements SimulationService {

    private final static int ZERODEAD = 0;
    private final static int ZAREOHEALED = 0;
    private final static int ZEROINFECTED = 0;
    private final static int ZERONOTINFECTED = 0;

    @Override
    public List<ProcessedSimulationData> processSimulation(InputSimulationData inputData) {
        List<ProcessedSimulationData> resultList = new ArrayList<>();

        for (int i = 0; i < inputData.getDaysToSimulate(); i++) {
            if (i == 0) {
                resultList.add(new ProcessedSimulationData(inputData.getInfected(), ZERODEAD, inputData.getPopulation() - inputData.getInfected(), ZAREOHEALED));
            } else {
                if (validateInfectedNumberLessThanPopulation(i,resultList,inputData)) {
                    resultList.add(new ProcessedSimulationData(
                            calculateInfected(i, resultList, inputData),
                            calculateDead(i, resultList, inputData),
                            calculateHealed(i, resultList, inputData),
                            calculateInfectedDaily(i, resultList, inputData),
                            calculateNotInfected(i, resultList, inputData)));
                } else {
                    resultList.add(new ProcessedSimulationData(
                            inputData.getPopulation(),
                            calculateDead(i, resultList, inputData),
                            calculateHealed(i, resultList, inputData),
                            ZEROINFECTED,
                            ZERONOTINFECTED));
                }
            }
        }
        return resultList;
    }

    private boolean validateInfectedNumberLessThanPopulation(int index,List<ProcessedSimulationData> resultList,InputSimulationData inputData ) {
       return calculateInfected(index,resultList,inputData) < inputData.getPopulation();
    }

    private int calculateHealed(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if(index - inputData.getDaysToHeal() < 0 || index - inputData.getDaysToHeal()+ inputData.getDaysToDie() > index){
            return 0;
        }else {
            return resultList.get(index - inputData.getDaysToHeal()).getInfectedDaily() - resultList.get(index - inputData.getDaysToHeal() + inputData.getDaysToDie()).getDiedDaily();
        }
    }

    private int calculateDead(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if(index - inputData.getDaysToDie() < 0) {
            return 0;
        }
        return (int) (inputData.getMortalityIndex() * resultList.get(index - inputData.getDaysToDie()).getInfectedDaily());
    }

    private int calculateInfectedDaily(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        return calculateInfected(index,resultList,inputData) - resultList.get(index - 1).getInfectedTotal();
    }

    private int calculateNotInfected(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
           return inputData.getPopulation() - calculateInfected(index,resultList,inputData) - calculateHealed(index, resultList, inputData) - calculateDead(index, resultList, inputData);
    }

    private int calculateInfected(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
                return (int) (resultList.get(index - 1).getInfectedTotal() * inputData.getRFactor());
    }

}
