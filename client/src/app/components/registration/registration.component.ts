import {Component} from "@angular/core";
import {Response} from "@angular/http";

import {RegistrationService} from "../../services/registration/registration.service";
import {Router} from "@angular/router";
import {LoginAndPassword} from "../../models/loginAndPassword";
import {ToasterService} from "../../services/ui/ToasterService";

@Component({

  selector: 'registration-component',
  templateUrl: '../../templates/registration.component.html',
  styleUrls: ['../../styles/registration.component.css']
})


export class RegistrationComponent {

  constructor(private registrationService: RegistrationService,
              private router: Router,
              private toasterService: ToasterService) {
  }

  loginAndPassword: LoginAndPassword = new LoginAndPassword("", "", "");
  adminSelected = false;

  registerUser() {
    this.registrationService.register(this.loginAndPassword).subscribe(
      (res: Response) => {
        if (res.status == 200) {
          this.toasterService.showToaster("Successfully registered!Now you can log in");
          console.log("User registered");
          this.router.navigate(['/']);
        }
      },
      ((err) => {
        if (err.status < 200 || err.status > 299) {
          this.loginAndPassword.login = "";
          this.loginAndPassword.password = "";
          this.loginAndPassword.serialNumber = "";
          this.toasterService.showToaster("Registration failed!");
          console.log("Registration failed")
        }
      }));
  };
}
