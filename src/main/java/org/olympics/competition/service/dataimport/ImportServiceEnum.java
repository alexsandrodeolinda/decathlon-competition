package org.olympics.competition.service.dataimport;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.service.dataimport.file.AthleteCsvImportService;

public enum ImportServiceEnum {
    CSV(2) {
        @Override
        public ImportService<Athlete> create(String filePath) {
            return new AthleteCsvImportService(filePath);
        }
    };

    ImportServiceEnum(int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    private int numberOfParameters;

    public abstract ImportService create(String parameter);

    public static ImportServiceEnum getInputDataType(String inputDataType) {
        for (ImportServiceEnum type: ImportServiceEnum.values()) {
            if (type.name().equals(inputDataType.toUpperCase())) {
                return type;
            }
        }

        return null;
    }

    public int getNumberOfParameters() {
        return numberOfParameters;
    }
}
