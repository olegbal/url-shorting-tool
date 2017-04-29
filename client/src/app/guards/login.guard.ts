import {Injectable}     from '@angular/core';
import {CanActivate, Router}    from '@angular/router';
import {AuthService} from "../services/auth/auth.service";

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private router: Router,private authService:AuthService) {
  }

  canActivate(): boolean {

    if (this.authService.isLoggedIn) {
      return false;
    }

    return true;
  }
}
