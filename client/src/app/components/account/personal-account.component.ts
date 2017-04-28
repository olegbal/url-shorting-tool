import {AfterContentChecked, Component, OnInit} from "@angular/core";


@Component({
  selector: 'personal-account',
  templateUrl: '../../templates/personal-account.component.html',
  styleUrls: ['../../styles/personal-account.component.css']
})

export class PersonalAccountComponent implements OnInit, AfterContentChecked {

  constructor() {
  }

  token: string = '';
  login: string = '';

  ngOnInit() {
    if (localStorage.getItem("Auth")) {
      this.token = localStorage.getItem("Auth");
      this.login = localStorage.getItem("Login");
    }
  }

  ngAfterContentChecked() {
    if (localStorage.getItem("Auth")) {
      this.token = localStorage.getItem("Auth");
      this.login = localStorage.getItem("Login");
    }
    else {
      this.token = '';
      this.login = '';
    }
  }

}
