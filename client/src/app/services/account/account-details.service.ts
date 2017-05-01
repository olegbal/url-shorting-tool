import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {AuthService} from "../auth/auth.service";

@Injectable()
export class AccountDetailsService {

  constructor(private http: Http,
              private authService: AuthService) {

  }

  private headers;

  private url = "/api/v1/account?";

  getUserInfo(userName: string) {
    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.get(this.url + 'userName=' + userName, {headers: this.headers})
  }

}
