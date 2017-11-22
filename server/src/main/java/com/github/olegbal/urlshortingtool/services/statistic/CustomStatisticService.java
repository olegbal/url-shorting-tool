package com.github.olegbal.urlshortingtool.services.statistic;

import com.github.olegbal.urlshortingtool.domain.Statistic;
import com.github.olegbal.urlshortingtool.repositories.StatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomStatisticService implements StatisticService {

    private StatisticRepository statisticRepository;

    @Autowired
    public CustomStatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public Statistic getStatistic() {
        return statisticRepository.findOne(1L);
    }

}
