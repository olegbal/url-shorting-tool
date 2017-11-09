import {Component} from "@angular/core";
import {Response} from "@angular/http";

import {RegistrationService} from "../../services/registration/registration.service";
import {Router} from "@angular/router";
import {LoginAndPassword} from "../../models/loginAndPassword";

@Component({

  selector: 'registration-component',
  templateUrl: '../../templates/registration.component.html',
  styleUrls: ['../../styles/registration.component.css','../../styles/spinner.css']
})


export class RegistrationComponent {

  constructor(private registrationService: RegistrationService,
              private router: Router) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "", "");
  adminSelected = false;
  spinnerOn=false;

  registerUser() {
    this.spinnerOn=true;
    this.registrationService.register(this.loginAndPassword).subscribe(
      (res: Response) => {
        if (res.status == 200) {
          console.log("User registered");
          this.spinnerOn=false;
          this.router.navigate(['/']);
        }
      },
      ((err) => {
        if (err.status < 200 || err.status > 299) {
          this.loginAndPassword.login = "";
          this.loginAndPassword.password = "";
          this.loginAndPassword.serialNumber = "";
          console.log("Registration failed");
          this.spinnerOn=false;
        }
      }));
  };
}
