package io.github.slotap.pandemiaapp.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatedInputSimulationData.class)
public @interface ValidInputSimulationData {
    String message() default "Input Simulation Data Incorrect";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
