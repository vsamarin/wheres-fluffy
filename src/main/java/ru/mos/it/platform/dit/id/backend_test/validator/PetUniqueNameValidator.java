package ru.mos.it.platform.dit.id.backend_test.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;
import org.springframework.stereotype.Component;
import ru.mos.it.platform.dit.id.backend_test.dto.PetDto;
import ru.mos.it.platform.dit.id.backend_test.repository.PetRepository;

@Component
@RequiredArgsConstructor
public class PetUniqueNameValidator implements ConstraintValidator<PetUniqueNameConstraint, PetDto> {

    private final PetRepository systemRepository;

    @Override
    public boolean isValid(PetDto petDto, ConstraintValidatorContext cxt) {
        if (cxt instanceof HibernateConstraintValidatorContext) {
            cxt.unwrap(HibernateConstraintValidatorContext.class).addMessageParameter("field", "name");
        }
        return (petDto.getId() == null) ?
                systemRepository.findOneByName(petDto.getName()).isEmpty() :
                systemRepository.findOneByIdNotAndName(petDto.getId(), petDto.getName()).isEmpty();
    }

}
