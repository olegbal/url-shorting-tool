import {Component} from "@angular/core";


import {Router} from "@angular/router";
import {LoginService} from "../../services/auth/login.service";
import {LoginAndPassword} from "../../models/loginAndPassword";
import {AuthService} from "../../services/auth/auth.service";
import {Role} from "../../models/role";
import {CustomToasterService} from "../../services/toaster/custom-toaster.service";

@Component({
  selector: 'login-component',
  templateUrl: '../../templates/login.component.html',
  styleUrls: ['../../styles/login.component.css', '../../styles/spinner.css']
})


export class LoginComponent {

  constructor(private loginService: LoginService,
              private router: Router,
              private authService: AuthService,
              private toasterService: CustomToasterService) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "");
  spinnerOn = false;

  logIn() {
    this.spinnerOn = true;
    this.loginService.logIn(this.loginAndPassword).subscribe(
      (res) => {
        if (res.status === 202) {
          let role: Role[] = res.json();
          if (role.find(x => x.roleName == "ROLE_ADMIN") != undefined) {
            this.authService.asAdmin = true;
          }
          this.authService.logIn(res.headers.get("Auth"), this.loginAndPassword.login);
          this.spinnerOn = false;
          this.toasterService.popToast("info","Logged in","Welcome, "+this.loginAndPassword.login);
          this.router.navigate(['/']);
        }
      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          this.loginAndPassword.login = "";
          this.loginAndPassword.password = "";
          console.log("Failed to log in", err);
          this.toasterService.popToast("error","Error","Failed to log in");
          this.spinnerOn = false;
        }
      }
    );
  }
}
