package org.olympics.competition.service.dataexport.file;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.service.dataexport.AbstractExportService;
import org.olympics.competition.service.dataexport.AthleteExportService;

import java.util.List;
import java.util.logging.Logger;

public class AthleteXmlExportService extends AbstractExportService<Athlete> implements AthleteExportService {
    private final static Logger LOGGER = Logger.getLogger(AthleteXmlExportService.class.getName());
    private String filePath;

    public AthleteXmlExportService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<Athlete> collection) {
        LOGGER.info("Export");
    }
}
