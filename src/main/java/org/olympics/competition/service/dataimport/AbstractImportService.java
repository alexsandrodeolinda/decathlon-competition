package org.olympics.competition.service.dataimport;

import org.olympics.competition.business.domain.Importable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractImportService<T extends Importable> {
    protected List<T> values = new ArrayList<>();

    protected List<T> getAll() {
        return this.values;
    }

    protected abstract void loadData();
}
