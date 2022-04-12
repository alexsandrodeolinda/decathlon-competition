package org.olympics.competition.service.dataimport.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.olympics.competition.business.domain.Athlete;

public class AthleteCsvParserTest {

    @Test
    void validateLine_WhenIsValid() {
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertDoesNotThrow(() -> parser.validateLine("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72"));
    }

    @Test
    void validateLine_WhenIsValid_Containing_Space() {
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertDoesNotThrow(() -> parser.validateLine("John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72 "));
    }

    @Test
    void validateLine_WhenPerformanceIsInValid() {
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.validateLine("John Smith;12.61a;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72"));
    }

    @Test
    void validateLine_WhenNameIsInValid() {
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertThrows(IllegalArgumentException.class, () -> parser.validateLine("12John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72"));
    }

    @Test
    void buildAthlete() {
        AthleteCsvParser parser = new AthleteCsvParser();
        String line = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";
        Assertions.assertDoesNotThrow(() -> parser.buildFromLine(line));
        Athlete a = parser.buildFromLine(line);
        Assertions.assertEquals("John Smith", a.getName());
        Assertions.assertArrayEquals(new double[]{12.61, 5.00, 9.22, 1.50, 60.39, 16.43, 21.60,  2.60,35.81, 325.72}, a.getPerformances());
    }

    @Test
    void hasValidName_WhenIsValid() {
        String line = "John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertTrue(parser.hasValidName(line));
    }

    @Test
    void hasValidName_WhenIsInValid() {
        String line = "1John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertFalse(parser.hasValidName(line));
    }

    @Test
    void lineHasExpectedFields_WhenIsValid() {
        String line = "1John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertTrue(parser.lineHasExpectedFields(line, ";"));
    }

    @Test
    void lineHasExpectedFields_WhenHasLessFields() {
        String line = "1John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertFalse(parser.lineHasExpectedFields(line, ";"));
    }

    @Test
    void lineHasExpectedFields_WhenHasMoreFields() {
        String line = "1John Smith;12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;5:25.72;21.60;21.22";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertFalse(parser.lineHasExpectedFields(line, ";"));
    }

    @Test
    void lineHasExpectedPerformances_WhenIsValid() {
        String line = "12.61;5.00;9.22;1.50;60.39;16.43;21.60;2.60;35.81;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertTrue(parser.lineHasExpectedPerformances(line));
    }

    @Test
    void lineHasExpectedPerformances_WhenIsInvalid() {
        String line = "John Smith;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertFalse(parser.lineHasExpectedPerformances(line));
    }

    @Test
    void hasValidPerformances_WhenIsInvalid() {
        String line = "John Smith;5:25.72";
        AthleteCsvParser parser = new AthleteCsvParser();
        Assertions.assertFalse(parser.hasValidPerformances(line));
    }
}
