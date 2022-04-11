package org.olympics.competition.service.dataimport;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImportService<T> {
    protected List<T> values = new ArrayList<>();

    protected List<T> getAll() {
        return this.values;
    }

    protected abstract void loadData();
}
