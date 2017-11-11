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

    @Column(name="created_links_count")
    private long createdLinksCount;

    @Column(name="registered_users_count")
    private long registeredUsersCount;

    @Column(name="user_accounts_amount")
    private long userAccountsAmount;

    @Column(name="administrator_accounts_amount")
    private long administratorAccountsAmount;

    @Column(name="total_clicks_count")
    private long totalClicksCount;

    @Column(name="top_user_id")
    private long topUserId;

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

    public long getCreatedLinksCount() {
        return createdLinksCount;
    }

    public void setCreatedLinksCount(long createdLinksCount) {
        this.createdLinksCount = createdLinksCount;
    }

    public long getRegisteredUsersCount() {
        return registeredUsersCount;
    }

    public void setRegisteredUsersCount(long registeredUsersCount) {
        this.registeredUsersCount = registeredUsersCount;
    }

    public long getUserAccountsAmount() {
        return userAccountsAmount;
    }

    public void setUserAccountsAmount(long userAccountsAmount) {
        this.userAccountsAmount = userAccountsAmount;
    }

    public long getAdministratorAccountsAmount() {
        return administratorAccountsAmount;
    }

    public void setAdministratorAccountsAmount(long administratorAccountsAmount) {
        this.administratorAccountsAmount = administratorAccountsAmount;
    }

    public long getTotalClicksCount() {
        return totalClicksCount;
    }

    public void setTotalClicksCount(long totalClicksCount) {
        this.totalClicksCount = totalClicksCount;
    }

    public long getTopUserId() {
        return topUserId;
    }

    public void setTopUserId(long topUserId) {
        this.topUserId = topUserId;
    }
}
