import {Injectable}     from '@angular/core';
import {CanActivate, Router}    from '@angular/router';

@Injectable()
export class LoginGuard implements CanActivate {

  constructor(private router: Router) {
  }

  canActivate(): boolean {

    if (localStorage.getItem("Login")) {
      return false;
    }

    return true;
  }
}
