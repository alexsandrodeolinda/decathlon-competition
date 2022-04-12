package org.olympics.competition.service.dataexport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExportServiceFactoryTest {

    @Test
    void getExportService() {
        ExportService service = new ExportServiceFactory().getExportService("xml", "xmlfile.xml");
        Assertions.assertNotNull(service);
        Assertions.assertEquals(AthleteXmlExportService.class,
                service.getClass());

    }
}