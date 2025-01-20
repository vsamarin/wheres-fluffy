package ru.mos.it.platform.dit.id.backend_test.repository.specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;
import ru.mos.it.platform.dit.id.backend_test.dto.SearchPetDto;
import ru.mos.it.platform.dit.id.backend_test.entity.PetEntity;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class PetSpecification implements Specification<PetEntity> {

    private final SearchPetDto search;

    @Override
    public Predicate toPredicate(
            @NonNull Root<PetEntity> root,
            @NonNull CriteriaQuery<?> query,
            @NonNull CriteriaBuilder cb
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (search.getName() != null) {
            predicates.add(cb.and(cb.like(cb.lower(root.get("name")), "%" + search.getName().toLowerCase() + "%")));
        }

        if (search.getSpecies() != null) {
            predicates.add(cb.and(cb.like(cb.lower(root.get("species")),
                    "%" + search.getSpecies().toLowerCase() + "%")));
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

}
