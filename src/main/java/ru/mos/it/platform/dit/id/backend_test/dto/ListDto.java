package ru.mos.it.platform.dit.id.backend_test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class ListDto<T> {
    private long totalElements;
    private int totalPages;
    private int size;
    private int numberOfElements;
    private boolean first;
    private List<T> content;
}
