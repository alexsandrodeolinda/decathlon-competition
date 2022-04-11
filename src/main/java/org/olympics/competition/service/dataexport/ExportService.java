package org.olympics.competition.service.dataexport;

import java.util.Collection;

public interface ExportService<T extends Collection> {
    void export(T collection);
}
