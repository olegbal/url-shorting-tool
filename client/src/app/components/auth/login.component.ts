import {Component} from "@angular/core";


import {Router} from "@angular/router";
import {LoginService} from "../../services/auth/login.service";
import {LoginAndPassword} from "../../models/loginAndPassword";
import {AuthService} from "../../services/auth/auth.service";
import {ToasterService} from "../../services/ui/ToasterService";

@Component({
  selector: 'login-component',
  templateUrl: '../../templates/login.component.html',
  styleUrls: ['../../styles/login.component.css']
})


export class LoginComponent {

  constructor(private loginService: LoginService,
              private router: Router,
              private authService: AuthService,
              private toasterService: ToasterService) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "");

  logIn() {
    this.loginService.logIn(this.loginAndPassword).subscribe(
      (res) => {
        if (res.status === 202) {
          this.authService.logIn(res.headers.get("Auth"), this.loginAndPassword.login);
          this.toasterService.showToaster("Successfully logged in");
          this.router.navigate(['/']);
        }
      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          this.loginAndPassword.login = "";
          this.loginAndPassword.password = "";
          this.toasterService.showToaster("Failed to log in");
          console.log("Failed to log in", err)
        }
      }
    );
  }
}
