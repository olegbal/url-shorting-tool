import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Router} from "@angular/router";

@Injectable()
export class AuthService {
  isLoggedIn: boolean = false;
  token: string = "";
  login: string = "";

  constructor(private router: Router) {

  }

  logIn(token: string, login: string) {
    this.isLoggedIn = true;
    this.token = token;
    this.login = login;
  }

  logout() {
    this.isLoggedIn = false;
    this.token = "";
    this.login = "";
    this.router.navigate(['/links']);
  }
}
