import {Injectable} from "@angular/core";
import {Http,Headers} from "@angular/http";

@Injectable()
export class AccountDetailsService {

  constructor(private http: Http) {

  }
  private headers;

  private url = "/api/v1/account?userName=";

  getUserInfo(userName: string) {
    this.headers = new Headers({'Auth': localStorage.getItem("Auth")});
    return this.http.get(this.url + userName, {headers: this.headers})
  }

}
