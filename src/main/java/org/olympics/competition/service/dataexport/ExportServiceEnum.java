package org.olympics.competition.service.dataexport;

import org.olympics.competition.business.domain.Athlete;

import java.util.List;

public enum ExportServiceEnum {
    XML(2) {
        public ExportService<List<Athlete>> create(String destinationPath) {
            return new AthleteXmlExportService(destinationPath);
        }
    };

    ExportServiceEnum( int numberOfParameters) {
        this.numberOfParameters = numberOfParameters;
    }

    private int numberOfParameters;

    public abstract ExportService create(String parameter);

    public int getNumberOfParameters() {
        return numberOfParameters;
    }

    public static ExportServiceEnum getOutputDataType(String outputDataType) {
        for (ExportServiceEnum type: ExportServiceEnum.values()) {
            if (type.name().equals(outputDataType.toUpperCase())) {
                return type;
            }
        }

        return null;
    }
}
