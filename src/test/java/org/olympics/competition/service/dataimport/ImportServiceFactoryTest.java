package org.olympics.competition.service.dataimport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.olympics.competition.service.dataimport.file.AthleteCsvImportService;

import static org.junit.jupiter.api.Assertions.*;

class ImportServiceFactoryTest {

    @Test
    void getImportService() {
        ImportService service = new ImportServiceFactory().getImportService(ImportServiceEnum.CSV, "file");
        Assertions.assertNotNull(service);
        Assertions.assertEquals(AthleteCsvImportService.class,
                service.getClass());
    }
}