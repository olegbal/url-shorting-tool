import {Injectable} from "@angular/core";
import {Headers, Http} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";

import {LoginAndPassword} from "../../models/loginAndPassword";


@Injectable()
export class RegistrationService {

  constructor(private http: Http) {
  }

  private urlString = "/api/v1/register";
  private headers = new Headers({'Content-Type': 'application/json'});


  register(loginAndPassword: LoginAndPassword) {
    return this.http.post(this.urlString, JSON.stringify(loginAndPassword), {headers: this.headers});
  }

}
