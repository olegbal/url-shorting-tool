export class Statistic {
  constructor(public statisticId: number,
              public deletedLinksCount: number,
              public createdLinksCount: number,
              public registeredUsersCount: number,
              public userAccountsAmount: number,
              public administratorAccountsAmount: number,
              public totalClicksCount: number,
              public topUserId: number){
  }
}
