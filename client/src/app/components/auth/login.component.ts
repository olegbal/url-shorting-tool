import {Component} from "@angular/core";


import {Router} from "@angular/router";
import {LoginService} from "../../services/auth/login.service";
import {LoginAndPassword} from "../../models/loginAndPassword";

@Component({
  selector: 'login-component',
  templateUrl: '../../templates/login.component.html',
  styleUrls: ['../../styles/login.component.css']
})


export class LoginComponent {

  constructor(private loginService: LoginService,
              private router: Router) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "");

  logIn() {
    this.loginService.logIn(this.loginAndPassword).subscribe(
      (res) => {
        if (res.status === 202) {
          this.loginAndPassword = res.json();

          localStorage.setItem("Auth", res.headers.get("Auth"));
          localStorage.setItem("Login", this.loginAndPassword.login);
          this.router.navigate(['/']);
        }
      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          console.log("Failed to log in", err)
        }
      }
    );
  }

}
