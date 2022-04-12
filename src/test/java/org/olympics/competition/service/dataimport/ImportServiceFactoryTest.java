package org.olympics.competition.service.dataimport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ImportServiceFactoryTest {

    @Test
    void getImportService() {
        ImportService service = new ImportServiceFactory().getImportService("csv", "file");
        Assertions.assertNotNull(service);
        Assertions.assertEquals(AthleteCsvImportService.class,
                service.getClass());
    }
}