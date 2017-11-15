package com.github.olegbal.urlshortingtool.repositories;

import com.github.olegbal.urlshortingtool.domain.Statistic;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StatisticRepository extends PagingAndSortingRepository<Statistic,Long> {

}
