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
//        Dropping existing triggers(avoiding sql exceptions)
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS deleted_links_count_trigger");
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS created_links_count_trigger");
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS registered_users_count_trigger");
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS user_accounts_divided_by_roles_counter_trigger");
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS total_clicks_count_trigger");
        jdbcTemplate.execute("DROP TRIGGER IF EXISTS top_user_trigger");

//        Triggers creation
        jdbcTemplate.execute(
                " CREATE TRIGGER deleted_links_count_trigger AFTER DELETE ON user_links FOR EACH ROW " +
                        "BEGIN " +
                            "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET deleted_links_count = deleted_links_count + 1 " +
                            "WHERE statistic_id = 1;" +
                        " END;");

        jdbcTemplate.execute(
                "CREATE TRIGGER created_links_count_trigger AFTER INSERT ON user_links FOR EACH ROW " +
                        "BEGIN " +
                            "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET created_links_count = created_links_count + 1 " +
                            "WHERE statistic_id = 1;" +
                        "END;"
        );

        jdbcTemplate.execute(
                "CREATE TRIGGER registered_users_count_trigger AFTER INSERT ON user_roles FOR EACH ROW " +
                        "BEGIN " +
                            "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET registered_users_count = registered_users_count + 1 " +
                            "WHERE statistic_id = 1;" +
                        "END;"
        );

        jdbcTemplate.execute(
                "CREATE TRIGGER user_accounts_divided_by_roles_counter_trigger AFTER INSERT ON user_roles FOR EACH ROW " +
                        "BEGIN " +
                            "IF NEW.role_id = 2 THEN " +
                                "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET administrator_accounts_amount = administrator_accounts_amount + 1 " +
                                "WHERE statistic_id = 1;" +
                            "ELSEIF NEW.role_id = 1 THEN " +
                                "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET user_accounts_amount = user_accounts_amount + 1 " +
                                "WHERE statistic_id = 1;" +
                            " END IF;" +
                        "END;"
        );

        jdbcTemplate.execute(
                " CREATE TRIGGER total_clicks_count_trigger AFTER UPDATE ON user_links FOR EACH ROW " +
                        "BEGIN " +
                            "IF NEW.clicks_count > OLD.clicks_count THEN "+
                                "UPDATE " + DbTableNames.STATISTICS_TABLE.toString() + " SET total_clicks_count = total_clicks_count + 1 " +
                                "WHERE statistic_id = 1;" +
                            "END IF;"+
                        "END;");

        jdbcTemplate.execute(
                "CREATE TRIGGER top_user_trigger AFTER INSERT ON user_links FOR EACH ROW " +
                        "BEGIN " +
                            "DECLARE top_user INT;"+
                            "DECLARE top_user_links_count INT;"+
                            "DECLARE creating_link_owner_links_count INT;"+

                            "SELECT top_user_id INTO top_user FROM statistics;"+
                            "SELECT COUNT(*) INTO top_user_links_count FROM user_links WHERE user_id=top_user;"+
                            "SELECT COUNT(*) INTO creating_link_owner_links_count FROM user_links WHERE user_id=NEW.user_id;"+

                            "IF top_user_links_count < creating_link_owner_links_count THEN"+
                                " UPDATE "+DbTableNames.STATISTICS_TABLE.toString() + " SET top_user_id = NEW.user_id"+
                                " WHERE statistic_id = 1;"+
                            "END IF;"+
                        "END;"
        );
    }
}
