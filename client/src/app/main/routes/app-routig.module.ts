import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {RegistrationComponent} from "../../components/registration/registration.component";
import {LoginComponent} from "../../components/auth/login.component";
import {AccountDetailsComponent} from "../../components/account/account-details.component";
import {AuthGuard} from "../../guards/auth.guard";
import {LinkListComponent} from "../../components/links/link-list.component";
import {LinkInfoComponent} from "../../components/links/link-info.component";
import {SameTagLinksComponent} from "../../components/links/same-tag-links.component";
import {RedirectorComponent} from "app/components/links/redirector.component";
import {LoginGuard} from "../../guards/login.guard";


const routes: Routes = [

  {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  {path: 'register', component: RegistrationComponent},
  {path: 'account', component: AccountDetailsComponent, canActivate: [AuthGuard]},
  {path: '', redirectTo: '/links', pathMatch: 'full'},
  {path: 'links', component: LinkListComponent},
  {path: 'links/:id', component: LinkInfoComponent},
  {path: 'links/tag/:tagName', component: SameTagLinksComponent},
  {path: '**', component: RedirectorComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
