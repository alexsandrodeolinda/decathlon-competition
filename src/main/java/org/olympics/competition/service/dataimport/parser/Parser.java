package org.olympics.competition.service.dataimport.parser;

import org.olympics.competition.exceptions.IncorrectFormatException;

public interface Parser<T, Object> {
    T parse(Object object) throws IncorrectFormatException;
}
