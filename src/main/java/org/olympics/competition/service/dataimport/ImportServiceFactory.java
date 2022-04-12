package org.olympics.competition.service.dataimport;

import java.util.logging.Logger;

public class ImportServiceFactory {
    private static final Logger LOGGER = Logger.getLogger(ImportServiceFactory.class.getName());
    public ImportService getImportService(String inputDataType, String parameter) {
        ImportServiceEnum serviceEnum = ImportServiceEnum.getInputDataType(inputDataType);
        return serviceEnum.create(parameter);
    }


}
