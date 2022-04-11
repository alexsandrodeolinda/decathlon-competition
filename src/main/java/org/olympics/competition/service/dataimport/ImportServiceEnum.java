package org.olympics.competition.service.dataimport;

import org.olympics.competition.service.dataimport.file.AthleteCsvImportService;

public enum ImportServiceEnum {
    CSV {
        @Override
        public ImportService create(String filePath) {
            return new AthleteCsvImportService(filePath);
        }
    };

    public abstract ImportService create(String parameter);

}
