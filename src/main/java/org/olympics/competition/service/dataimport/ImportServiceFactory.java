package org.olympics.competition.service.dataimport;

public class ImportServiceFactory {
    public ImportService getImportService(String inputDataType, String parameter) {
        ImportServiceEnum serviceEnum = ImportServiceEnum.getInputDataType(inputDataType);
        return serviceEnum != null ? serviceEnum.create(parameter) : null;
    }


}
