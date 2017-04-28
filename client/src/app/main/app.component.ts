import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  private redirectUrl = "/api/v1/shortlinks/";

  ngOnInit() {
    localStorage.setItem("RedirectUrl", this.redirectUrl);
  }

}
