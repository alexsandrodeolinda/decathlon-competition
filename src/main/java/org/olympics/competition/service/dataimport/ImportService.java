package org.olympics.competition.service.dataimport;

import java.util.List;

public interface ImportService<T> {
    List<T> getAll();
}
