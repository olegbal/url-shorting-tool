package com.github.olegbal.urlshortingtool.controllers;


import com.github.olegbal.urlshortingtool.services.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/v1")
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PreAuthorize("@userPreAuthorizeService.hasAdminRole(#request)")
    @RequestMapping(path = "/statistic",method = RequestMethod.GET)
    public ResponseEntity getStatistic(HttpServletRequest request){
        return new ResponseEntity(statisticService.getStatistic(), HttpStatus.OK);
    }
}
