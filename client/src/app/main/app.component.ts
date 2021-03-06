import {Component, OnInit} from "@angular/core";
import {AuthService} from "../services/auth/auth.service";
import {Router} from "@angular/router";
import {CustomToasterService} from "../services/toaster/custom-toaster.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private redirectUrl = "/api/v1/shortlinks/";

  private pageSize = "10";

  constructor(private authService: AuthService,
              private toasterService: CustomToasterService) {
  }

  ngOnInit() {
    localStorage.setItem("RedirectUrl", this.redirectUrl);
    localStorage.setItem("PageSize", this.pageSize);
  }

}
