package org.olympics.competition.service.dataexport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.FileExportException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AthleteXmlExportServiceTest {
    private List<Athlete> athletes;

    @Test
    void export() throws IOException {
        athletes = new ArrayList<>();
        Athlete smith = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60, 2.60, 35.81, 325.72});
        smith.setTotalScore(4200);
        athletes.add(smith);
        Athlete lukeSky = new Athlete("Luke Sky", new double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.20, 2.40, 28.20, 411.01});
        lukeSky.setTotalScore(7500);
        athletes.add(lukeSky);

        String outputFile = "src/test/resources/generated-output.xml";
        ExportService<List<Athlete>> exportService = new AthleteXmlExportService(outputFile);
        Assertions.assertDoesNotThrow(() -> exportService.export(athletes));
        File output = new File(outputFile);
        File template = new File("src/test/resources/output-template.xml");

        Assertions.assertTrue(Arrays.equals(Files.readAllBytes(template.toPath()), Files.readAllBytes(output.toPath())));
    }

    @Test
    void writeToFile() {
        athletes = new ArrayList<>();
        Athlete smith = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60, 2.60, 35.81, 325.72});
        smith.setTotalScore(4200);
        athletes.add(smith);
        Athlete lukeSky = new Athlete("Luke Sky", new double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.20, 2.40, 28.20, 411.01});
        lukeSky.setTotalScore(7500);
        athletes.add(lukeSky);

        String outputFile = "src/test/resources/generated-output-result.xml";
        ExportService<List<Athlete>> exportService = new AthleteXmlExportService(outputFile);
        Assertions.assertDoesNotThrow(() -> exportService.export(athletes));

        Assertions.assertTrue(new File(outputFile).exists());
    }

    @Test
    void writeXml() {
        athletes = new ArrayList<>();
        Athlete smith = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60, 2.60, 35.81, 325.72});
        smith.setTotalScore(4200);
        athletes.add(smith);
        Athlete lukeSky = new Athlete("Luke Sky", new double[]{13.04, 4.53, 7.79, 1.55, 64.72, 18.74, 24.20, 2.40, 28.20, 411.01});
        lukeSky.setTotalScore(7500);
        athletes.add(lukeSky);

        ExportService<List<Athlete>> exportService = new AthleteXmlExportService(null);
        Assertions.assertThrows(FileExportException.class, () -> exportService.export(athletes));
    }


}