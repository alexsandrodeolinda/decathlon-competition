package org.olympics.competition.application;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.business.domain.Ranking;
import org.olympics.competition.exceptions.IncorrectParamException;
import org.olympics.competition.service.calculation.AthleteRankingService;
import org.olympics.competition.service.calculation.RankingService;
import org.olympics.competition.service.calculation.ScoreCalculation;
import org.olympics.competition.service.calculation.ScoreCalculationService;
import org.olympics.competition.service.dataexport.ExportService;
import org.olympics.competition.service.dataexport.ExportServiceEnum;
import org.olympics.competition.service.dataexport.ExportServiceFactory;
import org.olympics.competition.service.dataimport.ImportService;
import org.olympics.competition.service.dataimport.ImportServiceEnum;
import org.olympics.competition.service.dataimport.ImportServiceFactory;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Comparator.comparing;

public class Application {
    private static final Logger LOGGER = Logger.getLogger(Application.class.getName());
    private final ScoreCalculation scoreCalculator;
    private final ImportServiceFactory importServiceFactory;
    private final ExportServiceFactory exportServiceFactory;
    private final RankingService rankingService;
    private final String[] args;

    public Application(String[] args,
                       ScoreCalculation scoreCalculator,
                       ImportServiceFactory importServiceFactory,
                       ExportServiceFactory exportServiceFactory,
                       RankingService rankingService) {
        this.args = args;
        this.scoreCalculator = scoreCalculator;
        this.importServiceFactory = importServiceFactory;
        this.exportServiceFactory = exportServiceFactory;
        this.rankingService = rankingService;
        this.initializeApp(args);
    }


    void initializeApp(String[] args) {
        validateArgs(args);

        validateArgsForDataType(ImportServiceEnum.getInputDataType(args[0]), ExportServiceEnum.getOutputDataType(args[2]), args);

        ImportService<Athlete> importService = importServiceFactory.getImportService(args[0], args[1]);
        ExportService<List<Athlete>> exportService = exportServiceFactory.getExportService(args[2], args[3]);

        List<Athlete> athleteList = importService.getAll();

        LOGGER.info(MessageFormat.format("Loaded athleteList = {0}", athleteList));

        for (Athlete athlete: athleteList) {
            int totalScore = this.scoreCalculator.getTotalScore(athlete.getPerformances());
            LOGGER.info(MessageFormat.format("athlete = {0} has total score = {1}", athlete.getName(), totalScore));
            athlete.setTotalScore(totalScore);
        }

        Ranking ranking = this.rankingService.buildOrderedRanking(athleteList, comparing(Athlete::getTotalScore).reversed());
        LOGGER.info("---- Ordered Ranking ----");
        for (Athlete a: ranking.getAthleteList()) {
            LOGGER.info(MessageFormat.format("athlete = {0}", a));
        }

        exportService.export(ranking.getAthleteList());

    }

    void validateArgsForDataType(ImportServiceEnum inputService, ExportServiceEnum exportService, String[] args) {
        if (inputService == null) {
            LOGGER.severe(MessageFormat.format("Cannot find a valid dataSource of type {0}. Valid values are {1}.", args[0], Arrays.asList(ImportServiceEnum.values())));
            throw new IncorrectParamException(MessageFormat.format("Cannot find a valid dataSource of type {0}", args[0]));
        }

        if (exportService == null) {
            LOGGER.severe(MessageFormat.format("Cannot find a valid output of type {0}. Valid values are {1}.", args[0], Arrays.asList(ExportServiceEnum.values())));
            throw new IncorrectParamException(MessageFormat.format("Cannot find a valid output of type {0}", args[0]));
        }

        int totalParameters = inputService.getNumberOfParameters() + exportService.getNumberOfParameters();

        if (totalParameters != args.length) {
            LOGGER.severe(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", inputService.name()));
            throw new IncorrectParamException(MessageFormat.format("Insufficient arguments to dataSource of type {0}. Please specify the type of datasource and file path.", inputService.name()));

        }
    }

    void validateArgs(String[] args) {
        if (args.length < 4) {
            LOGGER.severe(MessageFormat.format("Insufficient arguments to run the application. Please specify the type of input datasource, file path, output type and destination file path. Current valid values to input datasource and output type are: {0} and {1}", Arrays.asList(ImportServiceEnum.values()), Arrays.asList(ExportServiceEnum.values())));
            throw new IncorrectParamException(MessageFormat.format("Insufficient arguments to run the application. Please specify the type of input datasource, file path, output type and destination file path. Current valid values to input datasource and output type are: {0} and {1}", Arrays.asList(ImportServiceEnum.values()), Arrays.asList(ExportServiceEnum.values())));
        }
    }

    public static void main(String... args) {

        new Application(args, new ScoreCalculationService(), new ImportServiceFactory(), new ExportServiceFactory(), new AthleteRankingService());
    }
}
