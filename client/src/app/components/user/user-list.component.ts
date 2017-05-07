import {Component, OnInit} from "@angular/core";
import {Location} from "@angular/common";
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {ToasterService} from "app/services/ui/ToasterService";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";

@Component({

  selector: "users-list",
  templateUrl: "../../templates/user-list.component.html",
  styleUrls: ['../../styles/user-list.component.css', '../../styles/spinner.css']
})


export class UserListComponent implements OnInit {

  constructor(private userService: UserService,
              private toasterServer: ToasterService,
              private router: Router,
              private authService: AuthService) {
  }

  users: User[] = new Array<User>();
  spinnerOn = false;

  ngOnInit() {
    this.spinnerOn = true;
    this.userService.getRegisteredUsers().subscribe((res) => {

        if (res.status == 200) {
          this.users = res.json();
          console.log(this.users);
          this.spinnerOn = false;
        }
      },
      (err) => {

        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        } else if (err.status < 200 || err.status > 299) {
          console.log("Failed to get users", err);
          this.spinnerOn = false;
        }
      });
  }

  showUserInfo(login: string) {
    this.router.navigate(['/admin/users/' + login]);
  }

  removeUser(id: string) {
    this.userService.removeUserById(id).subscribe((res) => {
        if (res.status == 200) {
          this.toasterServer.showToaster("User deleted!");
          this.users = this.users.filter(x => x.userId != Number.parseInt(id));
        }
      },
      (err) => {

        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        } else if (err.status < 200 || err.status > 299) {
          this.toasterServer.showToaster("Failed to delete user!");
        }
      });
  }

}
