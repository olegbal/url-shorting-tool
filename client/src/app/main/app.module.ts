import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {FormsModule} from "@angular/forms";
import {HttpModule} from "@angular/http";
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';
// import {ModalModule} from 'ng2-modal-dialog/modal.module';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MaterialModule} from "@angular/material";
import "hammerjs";

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
// import {LinkExistsModal} from '../components/modals/link-exists.modal';

import {ToasterService} from "../services/ui/ToasterService";
import {DialogComponent} from "app/components/modals/dialog.component";


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
    DialogComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    FormsModule,
    HttpModule,
    AppRoutingModule,
    InfiniteScrollModule,
    MaterialModule.forRoot(),
    BrowserAnimationsModule,

  ],
  providers: [
    LoginService,
    RegistrationService,
    AccountDetailsService,
    AuthGuard,
    LinkService,
    LoginGuard,
    AuthService,
    ToasterService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
