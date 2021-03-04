package io.github.slotap.pandemiaapp.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InputSimulationDataTest {
    @Autowired
    private Validator validator;

    @Test
    public void shouldValidateIncorrectNumberOfInitialInfected() {
        // Given
        int infected = 100;
        int population = 50;
        InputSimulationData inputSimulationData = new InputSimulationData("Test",population,infected,1.0,1.0,10,10,10);

        // When
        Set<ConstraintViolation<InputSimulationData>> violations = validator.validate(inputSimulationData);

        // Then
        assertEquals(1, violations.size());
        assertEquals("Input Simulation Data Incorrect",violations.stream().findFirst().get().getMessage());
    }

    @Test
    public void shouldPassValidationCorrectNumberOfInitialInfected() {
        // Given
        int infected = 100;
        int population = 150;
        InputSimulationData inputSimulationData = new InputSimulationData("Test",population,infected,1.0,1.0,10,10,10);

        // When
        Set<ConstraintViolation<InputSimulationData>> violations = validator.validate(inputSimulationData);

        // Then
        assertEquals(0, violations.size());
    }

    @Test
    public void shouldValidateIncorrectMortalityIndex() {
        // Given
        double mortalityIndex = 2.0;

        InputSimulationData inputSimulationData = new InputSimulationData("Test",100,150,1.0,mortalityIndex,10,10,10);

        // When
        Set<ConstraintViolation<InputSimulationData>> violations = validator.validate(inputSimulationData);

        // Then
        assertEquals(1, violations.size());
        assertEquals("Input Simulation Data Incorrect",violations.stream().findFirst().get().getMessage());
    }

    @Test
    public void shouldPassValidationCorrectMortalityIndex() {
        // Given
        double mortalityIndex = 0.5;

        InputSimulationData inputSimulationData = new InputSimulationData("Test",100,150,1.0,mortalityIndex,10,10,10);

        // When
        Set<ConstraintViolation<InputSimulationData>> violations = validator.validate(inputSimulationData);

        // Then
        assertEquals(1, violations.size());
        assertEquals("Input Simulation Data Incorrect",violations.stream().findFirst().get().getMessage());
    }

}