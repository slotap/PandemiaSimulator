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

    private final static int ZERO = 0;

    @Override
    public List<ProcessedSimulationData> processSimulation(InputSimulationData inputData) {
        List<ProcessedSimulationData> resultList = new ArrayList<>();

        for (int i = 0; i < inputData.getDaysToSimulate(); i++) {
            if (i == 0) {
                resultList.add(new ProcessedSimulationData(inputData.getInfected(), ZERO, ZERO, inputData.getInfected(), inputData.getPopulation() - inputData.getInfected()));
            } else {
                    resultList.add(new ProcessedSimulationData(
                            calculateTotalInfected(i, resultList, inputData),
                            calculateDead(i, resultList, inputData),
                            calculateHealed(i, resultList, inputData),
                            calculateInfectedDaily(i, resultList, inputData),
                            calculateNotInfected(i, resultList, inputData)));
            }
        }
        return resultList;
    }

    private int calculateHealed(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if(index - inputData.getDaysToHeal() < 0 || index - inputData.getDaysToHeal()+ inputData.getDaysToDie() > index){
            return 0;
        }else {
            return resultList.get(index - inputData.getDaysToHeal()).getInfectedDaily() - calculateDailyIncreaseOfDead(index,resultList,inputData) + resultList.get(index - 1).getHealedDaily();
        }
    }

    private int calculateDead(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if(index - inputData.getDaysToDie() < 0) {
            return 0;
        }
        return (int) (inputData.getMortalityIndex() * resultList.get(index - inputData.getDaysToDie()).getInfectedDaily()) + resultList.get(index - 1).getDiedDaily();
    }

    private int calculateInfectedDaily(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
       if (validateSumInfectedHealedDeadGreaterThanPopulation(index,resultList,inputData)){
           return resultList.get(index - 1).getNotInfectedDaily();
       }
        return calculateInfected(index,resultList,inputData);
    }

    private int calculateNotInfected(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if (validateSumInfectedHealedDeadGreaterThanPopulation(index, resultList, inputData)) {
            return ZERO;
        }else{
            return inputData.getPopulation() - calculateTotalInfected(index, resultList, inputData) - calculateHealed(index, resultList, inputData) - calculateDead(index, resultList, inputData);
        }
    }

    private int calculateTotalInfected(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        if (validateSumInfectedHealedDeadGreaterThanPopulation(index, resultList, inputData)) {
            return inputData.getPopulation() - calculateDead(index, resultList, inputData) - calculateHealed(index, resultList, inputData);
        }else{
            return calculateInfectedDaily(index,resultList,inputData) + resultList.get(index - 1).getInfectedTotal()- calculateDead(index, resultList, inputData) - calculateHealed(index, resultList, inputData);
        }
    }

    private int calculateInfected(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        return (int) (resultList.get(index - 1).getInfectedTotal() * inputData.getRFactor());
    }

    private boolean validateSumInfectedHealedDeadGreaterThanPopulation(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData) {
        return calculateInfected(index, resultList, inputData) + resultList.get(index - 1).getHealedDaily() + resultList.get(index - 1).getDiedDaily() >= inputData.getPopulation();
    }

    private int calculateDailyIncreaseOfDead(int index, List<ProcessedSimulationData> resultList, InputSimulationData inputData){
        return resultList.get(index - inputData.getDaysToHeal() + inputData.getDaysToDie()).getDiedDaily() - resultList.get(index - inputData.getDaysToHeal() + inputData.getDaysToDie() - 1).getDiedDaily();
    }
}
