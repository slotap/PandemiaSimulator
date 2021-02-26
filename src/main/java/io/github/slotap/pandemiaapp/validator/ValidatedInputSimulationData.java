package io.github.slotap.pandemiaapp.validator;

import io.github.slotap.pandemiaapp.domain.InputSimulationData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatedInputSimulationData implements ConstraintValidator <ValidInputSimulationData, InputSimulationData>{

    @Override
    public boolean isValid(InputSimulationData inputSimulationData, ConstraintValidatorContext constraintValidatorContext) {
        return inputSimulationData.getPopulation() >= inputSimulationData.getInfected() && inputSimulationData.getMortalityIndex() <= 1;
    }
}
