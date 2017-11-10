package com.github.olegbal.urlshortingtool.services;

import com.github.olegbal.urlshortingtool.enums.DbTableNames;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TriggersService {

    private JdbcTemplate jdbcTemplate;

    public TriggersService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void executeScripts() {
//        jdbcTemplate.execute("DROP TRIGGER IF EXISTS del_sum");
//        jdbcTemplate.execute(
//                " CREATE TRIGGER del_sum AFTER DELETE ON user_links FOR EACH ROW " +
//                "BEGIN " +
//                "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET deleted_links_count = deleted_links_count + 1 " +
//                "WHERE statistic_id = 1;" +
//                " END;");
    }
}
