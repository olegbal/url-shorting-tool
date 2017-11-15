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
import {UserListComponent} from "../../components/user/user-list.component";
import {UserInfoComponent} from "../../components/user/user-details.component";
import {AdminCabinetGuard} from "../../guards/admin-cabinet.guard";

const routes: Routes = [

  {path: 'login', component: LoginComponent, canActivate: [LoginGuard]},
  {path: 'register', component: RegistrationComponent},
  {path: 'account', component: AccountDetailsComponent, canActivate: [AuthGuard]},
  {path: '', redirectTo: '/links', pathMatch: 'full'},
  {path: 'links', component: LinkListComponent},
  {path: 'links/:id', component: LinkInfoComponent},
  {path: 'links/tag/:tagName', component: SameTagLinksComponent},
  {path: 'admin/users', component: UserListComponent,canActivate:[AdminCabinetGuard]},
  {path: 'admin/users/:userName',component: UserInfoComponent,canActivate:[AdminCabinetGuard]},
  //add statistics routing. TODO Add statistics component
  {path: '**', component: RedirectorComponent}, //must be at the end of routes list.

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
