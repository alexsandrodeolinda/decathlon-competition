package org.olympics.competition.service.dataimport;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.IncorrectFormatException;
import org.olympics.competition.exceptions.IncorrectParamException;
import org.olympics.competition.service.dataimport.parser.AthleteCsvParser;
import org.olympics.competition.service.dataimport.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class AthleteCsvImportService extends AbstractImportService<Athlete> implements AthleteImportService {

    private final static Logger LOGGER = Logger.getLogger(AthleteCsvImportService.class.getName());
    public static final String DELIMITER = ";";
    private String filePath;

    AthleteCsvImportService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<Athlete> getAll() {
        loadData();
        return super.getAll();
    }

    @Override
    protected void loadData() {
        if (this.filePath == null) {
            throw new IncorrectParamException("File path must cannot be null");
        }
        LOGGER.info(MessageFormat.format("Loading data from Csv, filePath = {0}", filePath));
        List<Athlete> records = retrieveLinesFromFile(filePath);
        this.values = records;
        LOGGER.info("Data loaded successfully");
    }

    /**
     * Each line is expected to be in the format 'Jane Doe;13.04;4.53;7.79;1.55;64.72;18.74;24.20;2.40;28.20;6:50.76'
     * As the format is well-known and can be easily described to the clients, I'm throwing an IllegalArgumentException
     * @return
     */
    protected List<Athlete> retrieveLinesFromFile(String filePath) {
        List<Athlete> records = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath));) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                if (!line.isEmpty()) {
                    records.add(getAthleteFromLine(line));
                }
            }
        } catch (FileNotFoundException e) {
            LOGGER.severe(MessageFormat.format("The CSV file {0} could not be found.", filePath));
            throw new IncorrectParamException(e.getMessage());
        } catch (IncorrectFormatException e) {
            LOGGER.severe(e.getMessage());
            throw new IllegalArgumentException();
        }

        return records;
    }
    
    public Athlete getAthleteFromLine(String line) throws IncorrectFormatException {
        Parser parser = new AthleteCsvParser();
        Athlete athlete = (Athlete) parser.parse(line);

        return athlete;
    }

}
