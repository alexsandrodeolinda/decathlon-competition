package org.olympics.competition.service.dataimport;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.service.dataimport.file.AthleteCsvImportService;

public enum ImportServiceEnum {
    CSV {
        @Override
        public ImportService<Athlete> create(String filePath) {
            return new AthleteCsvImportService(filePath);
        }
    };

    public abstract ImportService create(String parameter);

}
