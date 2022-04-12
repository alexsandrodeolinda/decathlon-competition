package org.olympics.competition.application;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.IncorrectParamException;
import org.olympics.competition.service.calculation.ScoreCalculation;
import org.olympics.competition.service.calculation.ScoreCalculationService;
import org.olympics.competition.service.dataimport.ImportService;
import org.olympics.competition.service.dataimport.ImportServiceEnum;
import org.olympics.competition.service.dataimport.ImportServiceFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private final ScoreCalculation scoreCalculator;
    private final ImportServiceFactory importServiceFactory;
    private final String[] args;

    public Application(String[] args, ScoreCalculation scoreCalculator, ImportServiceFactory importServiceFactory) {
        this.args = args;
        this.scoreCalculator = scoreCalculator;
        this.importServiceFactory = importServiceFactory;
        this.initializeApp(args);
    }


    void initializeApp(String[] args) {
        validateArgs(args);

        validateArgsForDataType(ImportServiceEnum.getInputDataType(args[0]), args);

        ImportService<Athlete> fromCsv = importServiceFactory.getImportService(args[0], args[1]);

        List<Athlete> athleteList = fromCsv.getAll();

        LOGGER.info(MessageFormat.format("Loaded athleteList = {0}", athleteList));

        for (Athlete athlete: athleteList) {
            int totalScore = this.scoreCalculator.getTotalScore(athlete.getPerformances());
            System.out.println("athlete = " + athlete.getName() + " has total score = " + totalScore);
        }
    }

    ImportServiceEnum getInputDataType(String inputDataType) {
        for (ImportServiceEnum type: ImportServiceEnum.values()) {
            if (type.name().equals(inputDataType.toUpperCase())) {
                return type;
            }
        }
        LOGGER.severe(MessageFormat.format("Cannot find a valid dataSource of type {0}. Valid values are {1}.", inputDataType, Arrays.asList(ImportServiceEnum.values())));
        throw new IncorrectParamException(MessageFormat.format("Cannot find a valid dataSource of type {0}", inputDataType));
    }

    void validateArgsForDataType(ImportServiceEnum serviceType, String[] args) {
        if (serviceType.getNumberOfParameters() != args.length) {
            LOGGER.severe(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", serviceType.name()));
            throw new IncorrectParamException(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", serviceType.name()));

        }
//        if (serviceType.equals(ImportServiceEnum.CSV) && args.length < 2) {
//            LOGGER.severe(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", serviceType.name()));
//            throw new IncorrectParamException(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", serviceType.name()));
//        }
    }

    void validateArgs(String[] args) {
        if (args.length == 0) {
            LOGGER.severe(MessageFormat.format("Insufficient arguments to run the application. Please specify the type of datasource and file path. Current valid values to datasource are: {0}", Arrays.asList(ImportServiceEnum.values())));
            throw new IncorrectParamException(MessageFormat.format("Insufficient arguments to run the application. Please specify the type of data and file path. Current valid values to datasource are: {0}", Arrays.asList(ImportServiceEnum.values())));
        }
    }

    public static void main(String... args) {

        new Application(args, new ScoreCalculationService(), new ImportServiceFactory());
    }
}
