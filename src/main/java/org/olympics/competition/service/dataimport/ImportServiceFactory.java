package org.olympics.competition.service.dataimport;

public class ImportServiceFactory {
    public ImportService getImportService(ImportServiceEnum serviceEnum, String parameter) {
        return serviceEnum.create(parameter);
    }
}
