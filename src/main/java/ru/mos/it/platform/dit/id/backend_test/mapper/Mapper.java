package ru.mos.it.platform.dit.id.backend_test.mapper;

import org.springframework.data.domain.Page;
import ru.mos.it.platform.dit.id.backend_test.dto.ListDto;

public interface Mapper<F, T> {

    T map(F object);

    default ListDto<T> map(Page<T> page) {
        return page == null ? null : new ListDto<T>()
                .setTotalElements(page.getTotalElements())
                .setTotalPages(page.getTotalPages())
                .setSize(page.getSize())
                .setNumberOfElements(page.getNumberOfElements())
                .setFirst(page.isFirst())
                .setContent(page.getContent());
    }

}
