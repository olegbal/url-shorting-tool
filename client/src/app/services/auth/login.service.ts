import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

import {Observable} from "rxjs";
import {LoginAndPassword} from "../../models/loginAndPassword";


@Injectable()
export class LoginService {

  constructor(private http: Http) {
  }

  private headers = new Headers({'Content-Type': 'application/json'});

  private urlString = "/api/v1/login";

  logIn(loginAndPassword: LoginAndPassword): Observable<Response> {
    return this.http.post(this.urlString, JSON.stringify(loginAndPassword), {headers: this.headers})
  }
}
