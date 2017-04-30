import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";

import {AppComponent} from "./app.component";
import {AppRoutingModule} from "./routes/app-routig.module";
import {LoginService} from "../services/auth/login.service";
import {RegistrationService} from "../services/registration/registration.service";
import {RegistrationComponent} from "../components/registration/registration.component";
import {LoginComponent} from "../components/auth/login.component";
import {AccountDetailsComponent} from "../components/account/account-details.component";
import {AccountDetailsService} from "../services/account/account-details.service";
import {AuthGuard} from "../guards/auth.guard";
import {LinkService} from "../services/links/link.service";
import {LinkListComponent} from "../components/links/link-list.component";
import {LinkInfoComponent} from "../components/links/link-info.component";
import {SameTagLinksComponent} from "../components/links/same-tag-links.component";
import {RedirectorComponent} from "../components/links/redirector.component";
import {LoginGuard} from "app/guards/login.guard";
import {AuthService} from "app/services/auth/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    AccountDetailsComponent,
    LinkListComponent,
    LinkInfoComponent,
    SameTagLinksComponent,
    RedirectorComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
  ],
  providers: [
    LoginService,
    RegistrationService,
    AccountDetailsService,
    AuthGuard,
    LinkService,
    LoginGuard,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
