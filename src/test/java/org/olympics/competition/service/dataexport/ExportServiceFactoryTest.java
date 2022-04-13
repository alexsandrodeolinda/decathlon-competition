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

    @Test
    void getExportService_WhenDoesNotExist() {
        ExportService service = new ExportServiceFactory().getExportService("db", "jdbc://abscd/db_des_SQLServer:3441");
        Assertions.assertNull(service);


    }
}