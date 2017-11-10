package com.github.olegbal.urlshortingtool.domain;

import javax.persistence.*;

@Entity
@Table(name="statistics")
public class Statistic {

    @Id
    @Column(name="statistic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long statisticId;

    @Column(name="deleted_links_count")
    private long deletedLinksCount;



//    @Column(name="total_clicks_count")
//    private long totalClicksCount;
//
//    @Column(name="registered_users_count")
//    private long registeredUsersCount;
//
//    private long registeredAdministrators;
//
//    private long usersCount;

    public long getStatisticId() {
        return statisticId;
    }

    public void setStatisticId(long statisticId) {
        this.statisticId = statisticId;
    }

    public long getDeletedLinksCount() {
        return deletedLinksCount;
    }

    public void setDeletedLinksCount(long deletedLinksCount) {
        this.deletedLinksCount = deletedLinksCount;
    }
}
