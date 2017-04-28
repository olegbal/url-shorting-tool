import {Component} from "@angular/core";
import {Response} from "@angular/http";

import {RegistrationService} from "../../services/registration/registration.service";
import {Router} from "@angular/router";
import {LoginAndPassword} from "../../models/loginAndPassword";

@Component({

  selector: 'registration-component',
  templateUrl: '../../templates/registration.component.html',
  styleUrls: ['../../styles/registration.component.css']
})


export class RegistrationComponent {

  constructor(private registrationService: RegistrationService,
              private router: Router) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "");

  registerUser() {
    this.registrationService.register(this.loginAndPassword).subscribe(
      (res: Response) => {
        if (res.status == 200) {
          this.router.navigate(['/']);
        }
      },
      ((err) => {
        if (err.status < 200 || err.status > 299) {

          console.log("Registration failed")
        }
      }));
  };
}
