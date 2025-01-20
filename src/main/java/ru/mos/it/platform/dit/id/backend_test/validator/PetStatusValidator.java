package ru.mos.it.platform.dit.id.backend_test.validator;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ru.mos.it.platform.dit.id.backend_test.entity.PetStatus;

import java.util.Arrays;

public class PetStatusValidator implements ConstraintValidator<PetStatusConstraint, PetStatus> {

    private PetStatus[] subset;

    @Override
    public void initialize(PetStatusConstraint constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(PetStatus value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }

}
