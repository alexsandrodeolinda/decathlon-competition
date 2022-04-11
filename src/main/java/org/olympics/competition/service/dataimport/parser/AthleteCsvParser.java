package org.olympics.competition.service.dataimport.parser;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.IncorrectFormatException;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AthleteCsvParser implements AthleteParser {

    public static final String CSV_DELIMITER = ";";
    public static final int NUMBER_OF_FIELDS = 11;
    public static final int MINUTE_TO_SEC = 60;
    public static final String MILISECOND_DELIMITER = ".";
    public static final String SECOND_DELIMITER = ":";

    @Override
    public Athlete parse(String object) throws IncorrectFormatException {
        try {
            return buildFromLine(object);
        } catch (IllegalArgumentException iae) {
            throw new IncorrectFormatException(MessageFormat.format("Invalid record format {0}", object));
        }

    }

    void validateLine(String line) {
        if (!lineHasExpectedFields(line, CSV_DELIMITER) ||
                !hasValidName(line) ||
                    !hasValidPerformances(line)) {
            throw new IllegalArgumentException();
        }
    }

    Athlete buildFromLine(String line) throws IllegalArgumentException {
        validateLine(line);

        String name = getNameFromLine(line);
        double[] performances = getPerformancesArray(line);

        Athlete athlete = new Athlete(name, performances);
        return athlete;
    }

    String[] splitLine(String content, String pattern) {
        return content.split(pattern);
    }

    boolean hasValidName(String line) {
        final String name = splitLine(line, CSV_DELIMITER)[0];
        final String regex = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)";
        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(name);

        return matcher.find();
    }

    boolean lineHasExpectedFields(String line, String pattern) {
        return this.splitLine(line, pattern).length == NUMBER_OF_FIELDS;
    }

    boolean lineHasExpectedPerformances(String performances) {
        return splitLine(performances, CSV_DELIMITER).length == NUMBER_OF_FIELDS - 1;
    }

    boolean hasValidPerformances(String content) {
        String performances = getPerformancesFromLine(content);

        if (!lineHasExpectedPerformances(performances)) {
            return false;
        }

        String[] perfArray = splitLine(performances, CSV_DELIMITER);

        for (int i = 0; i < perfArray.length; i++) {
            String result = perfArray[i].trim();
            boolean valid = hasValidPerformance(result);
            if (!valid) {
                return false;
            }
        }

        return true;
    }

    protected String replaceSpacesInBetween(String performances) {
        return performances.replaceAll(" ", "");
    }

    private String getPerformancesFromLine(String content) {
        int firstDelimiterIndex = content.indexOf(CSV_DELIMITER);
        String performances = content.substring(firstDelimiterIndex + 1);
        return performances;
    }

    String getNameFromLine(String line) {
        return this.splitLine(line, CSV_DELIMITER)[0];
    }

    double[] getPerformancesArray(String line) {
        String trimmedLine = replaceSpacesInBetween(line);
        String[] performancesSt = Arrays.copyOfRange(splitLine(trimmedLine, CSV_DELIMITER), 1, NUMBER_OF_FIELDS);
        double[] performances = new double[performancesSt.length];
        for (int i = 0; i < performancesSt.length; i++) {
            if (isExpressedInSeconds(performancesSt[i])) {
                performances[i] = Double.parseDouble(performancesSt[i]);
            } else if (isExpressedInMinutes(performancesSt[i])) {
                String[] parts = performancesSt[i].split(":|\\.");
                int minutes = Integer.parseInt(parts[0]);
                performances[i] = minutesToSeconds(minutes) + Double.parseDouble(parts[1] + MILISECOND_DELIMITER + parts[2]);
            }

        }
        return performances;
    }

    private int minutesToSeconds(int minutes) {
        return minutes * MINUTE_TO_SEC;
    }

    boolean hasValidPerformance(String performance) {
        return hasValidPerformanceInSeconds(performance) || hasValidPerformanceInMinutes(performance);
    }

    boolean isExpressedInSeconds(String performance) {
        return hasValidPerformanceInSeconds(performance);
    }

    boolean isExpressedInMinutes(String performance) {
        return hasValidPerformanceInMinutes(performance);
    }

    boolean hasValidPerformanceInMinutes(String performance) {
        final String regex = "^[0-5]?\\d:[0-5]\\d?(?:[.,][0-9]{1,3})?$";
        final String string = performance; // example "5:25.111";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return  matcher.matches();
    }

    boolean hasValidPerformanceInSeconds(String performance) {
        final String regex = "^(?:[1-9]\\d*|0)?(?:\\.\\d+)?$";
        final String string = performance; //"5.33";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
        return  matcher.matches();
    }


}
