package ru.mos.it.platform.dit.id.backend_test.validator;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.mos.it.platform.dit.id.backend_test.entity.PetStatus;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PetStatusValidator.class)
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PetStatusConstraint {
    PetStatus[] anyOf();

    String message() default "must be any of {anyOf}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
