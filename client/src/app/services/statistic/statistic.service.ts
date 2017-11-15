import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs";
import {AuthService} from "../auth/auth.service";


@Injectable()
export class StatisticService {

  constructor(private http: Http,
              private authService: AuthService) {
  }

  private urlString = "/api/v1/statistic";
  private headers;

  getStatistic(){
    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.get(this.urlString, {headers: this.headers});
  }

}
