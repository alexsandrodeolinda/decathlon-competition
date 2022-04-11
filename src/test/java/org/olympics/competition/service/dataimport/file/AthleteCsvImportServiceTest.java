package org.olympics.competition.service.dataimport.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.IncorrectFormatException;
import org.olympics.competition.exceptions.IncorrectParamException;
import org.olympics.competition.service.dataimport.ImportService;
import org.olympics.competition.service.dataimport.parser.Parser;

import java.util.List;
@ExtendWith(MockitoExtension.class)
public class AthleteCsvImportServiceTest {
    @Mock
    Parser parser;

    @Test
    void getAll_WhenFileNotFound() {
        ImportService service = new AthleteCsvImportService("/opt/files/results.csv");
        Assertions.assertThrows(IncorrectParamException.class, () -> service.getAll());

    }

    @Test
    void getAll_WhenFileNotSet() {
        ImportService service = new AthleteCsvImportService(null);
        Assertions.assertThrows(IncorrectParamException.class, () -> service.getAll());

    }

    @Test
    void getAll_WhenFileFoundAndHasRecords() {
        ImportService service = new AthleteCsvImportService("src/test/resources/results.csv");
        List<Athlete> athletes = service.getAll();
        Assertions.assertFalse(athletes.isEmpty());
        Assertions.assertEquals("John Smith", athletes.get(0).getName());

    }

    @Test
    void getAll_WhenFileFoundAndHasNoRecords() {
        ImportService service = new AthleteCsvImportService("src/test/resources/results-empty.csv");
        List<Athlete> athletes = service.getAll();
        Assertions.assertTrue(athletes.isEmpty());

    }

    @Test
    void getAthleteFromLine() throws IncorrectFormatException {
        AthleteCsvImportService service = new AthleteCsvImportService("src/test/resources/results.csv");

        Athlete a = new Athlete("John Smith", new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72});
        String line = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";

        Mockito.lenient().when(parser.parse(line)).thenReturn(a);

        Assertions.assertEquals(a, service.getAthleteFromLine(line));
    }
}
