import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Router} from "@angular/router";
import {User} from "../../models/user";

@Injectable()
export class AuthService {
  isLoggedIn: boolean = false;
  token: string = "";
  login: string = "";
  asAdmin:boolean  = false;

  constructor(private router: Router) {
    this.token = localStorage.getItem("Token") || "";
    this.login = localStorage.getItem("Login") || "";
    let temp = localStorage.getItem("asAdmin") || "false";
    if (temp.includes("true")) {
      this.asAdmin = true;
    }
    if (this.token !== "" && this.login !== "") {
      this.isLoggedIn = true;
    }
  }

  logIn(token: string, login: string) {
    if (this.asAdmin) {
      localStorage.setItem("asAdmin", "true");
    }
    this.isLoggedIn = true;
    localStorage.setItem("Token", token);
    localStorage.setItem("Login", login);
    this.token = token;
    this.login = login;
  }

  logout() {
    this.isLoggedIn = false;
    this.token = "";
    this.login = "";
    this.asAdmin = false;
    localStorage.removeItem("Token");
    localStorage.removeItem("Login");
    localStorage.removeItem("asAdmin");
    this.router.navigate(['/links']);
  }
}
