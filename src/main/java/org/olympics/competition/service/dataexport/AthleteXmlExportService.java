package org.olympics.competition.service.dataexport;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.business.domain.DecathlonEvent;
import org.olympics.competition.exceptions.FileExportException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Logger;

public class AthleteXmlExportService extends AbstractExportService<Athlete> implements AthleteExportService {
    private final static Logger LOGGER = Logger.getLogger(AthleteXmlExportService.class.getName());
    private String filePath;

    AthleteXmlExportService(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export(List<Athlete> athletes) {
        LOGGER.info("Export");
        this.writeToFile(athletes);
    }

    void writeToFile(List<Athlete> athletes) {
        if (this.filePath == null) {
            throw new FileExportException("File path must cannot be null");
        }
        LOGGER.info(String.format("Export ranking to %s", filePath));
        Document doc = null;
        try {
            doc = createDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        insertElements(doc, athletes);

        try (FileOutputStream output =
                     new FileOutputStream(this.filePath)) {
            writeXml(doc, output);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    void writeXml(Document doc, OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    void insertElements(Document doc, List<Athlete> athletesList) {
        Element rootElement = doc.createElement("decathlon");
        doc.appendChild(rootElement);

        Element rankingElement = doc.createElement("ranking");


        for (Athlete athlete: athletesList) {
            Element athleteElement = doc.createElement("athlete");

            Element name = doc.createElement("name");
            name.setTextContent(athlete.getName());
            athleteElement.appendChild(name);

            Element totalScore = doc.createElement("totalScore");
            totalScore.setTextContent(String.valueOf(athlete.getTotalScore()));
            athleteElement.appendChild(totalScore);

            Element place = doc.createElement("place");
            place.setTextContent(athlete.getPlace());
            athleteElement.appendChild(place);

            Element allEventsElement = doc.createElement("events");
            for (int i = 0; i < athlete.getPerformances().length; i++) {
                Element event = doc.createElement("event");
                event.setTextContent(DecathlonEvent.values()[i].getName());

                Element performance = doc.createElement("performance");
                performance.setTextContent(String.valueOf(athlete.getPerformances()[i]));
                event.appendChild(performance);

                allEventsElement.appendChild(event);
            }

            athleteElement.appendChild(allEventsElement);
            rankingElement.appendChild(athleteElement);


        }
        rootElement.appendChild(rankingElement);
    }

    Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        return doc;
    }
}
