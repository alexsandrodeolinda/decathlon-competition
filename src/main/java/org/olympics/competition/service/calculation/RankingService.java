package org.olympics.competition.service.calculation;

import org.olympics.competition.business.domain.Ranking;

import java.util.Comparator;
import java.util.List;

public interface RankingService<T> {
    Ranking buildOrderedRanking(List<T> collection, Comparator<T> comparator);
}
