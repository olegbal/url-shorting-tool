import {Injectable}     from '@angular/core';
import {CanActivate, Router}    from '@angular/router';
import {AuthService} from "../services/auth/auth.service";
import {AccountDetailsService} from "../services/account/account-details.service";

@Injectable()
export class AdminCabinetGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthService) {
  }

  canActivate(): boolean {

    if (this.authService.asAdmin) {
      return true;
    }

    this.router.navigate(["/login"]);
    return false;
  }
}
