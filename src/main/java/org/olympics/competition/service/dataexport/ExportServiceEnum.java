package org.olympics.competition.service.dataexport;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.service.dataexport.file.AthleteXmlExportService;

import java.util.List;

public enum ExportServiceEnum {
    XML {
        public ExportService<List<Athlete>> create(String destinationPath) {
            return new AthleteXmlExportService(destinationPath);
        }
    };

    public abstract ExportService create(String parameter);
}
