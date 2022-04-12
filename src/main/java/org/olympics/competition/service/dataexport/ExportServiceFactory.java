package org.olympics.competition.service.dataexport;


import java.util.logging.Logger;

public class ExportServiceFactory {
    private static final Logger LOGGER = Logger.getLogger(ExportServiceFactory.class.getName());
    public ExportService getExportService(String outputDataType, String parameter) {
        ExportServiceEnum serviceEnum = ExportServiceEnum.getOutputDataType(outputDataType);
        return serviceEnum.create(parameter);
    }
}
