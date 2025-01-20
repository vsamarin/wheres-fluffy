package ru.mos.it.platform.dit.id.backend_test.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PetUniqueNameValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PetUniqueNameConstraint {
    String message() default "\"{field}\" must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
