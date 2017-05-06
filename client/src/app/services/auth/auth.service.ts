import {Injectable} from "@angular/core";
import "rxjs/add/observable/of";
import "rxjs/add/operator/do";
import "rxjs/add/operator/delay";
import {Router} from "@angular/router";
import {User} from "../../models/user";
import {ToasterService} from "app/services/ui/ToasterService";

@Injectable()
export class AuthService {
  isLoggedIn: boolean = false;
  token: string = "";
  login: string = "";
  asAdmin = true; //????

  constructor(private router: Router, private toasterService: ToasterService) {
    this.token = localStorage.getItem("Token") || "";
    this.login = localStorage.getItem("Login") || "";

    if (this.token !== "" && this.login !== "") {
      this.isLoggedIn = true;
    }
  }

  logIn(token: string, login: string) {
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
    localStorage.removeItem("Auth");
    localStorage.removeItem("Login");
    this.router.navigate(['/links']);
    this.toasterService.showToaster("Logged out")
  }

  isAdmin(user: User): boolean {

    if (user.roles.find(x => x.roleName == "ROLE_ADMIN") != undefined) {
      return true;
    }

    return false;
  }
}
