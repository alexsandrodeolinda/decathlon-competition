package org.olympics.competition.service.dataimport.parser;

import org.olympics.competition.business.domain.Importable;
import org.olympics.competition.exceptions.IncorrectFormatException;

public interface Parser<T extends Importable, Object> {
    T parse(Object object) throws IncorrectFormatException;
}
