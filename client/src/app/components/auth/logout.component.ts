import {Component} from "@angular/core";

@Component({
  selector: 'logout-component',
  templateUrl: '../../templates/logout.component.html',
  styleUrls: ['../../styles/logout.component.css']
})

export class LogoutComponent {

  constructor() {
  }

  logOut() {
    localStorage.removeItem("Auth");
    localStorage.removeItem("Login");
  }
}
