package org.olympics.competition.service.dataimport.parser;

import org.olympics.competition.business.domain.Athlete;
import org.olympics.competition.exceptions.IncorrectFormatException;

public interface AthleteParser extends Parser<Athlete, String> {
    @Override
    Athlete parse(String object) throws IncorrectFormatException;
}
