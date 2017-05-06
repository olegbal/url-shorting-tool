import {Component, OnInit} from "@angular/core";
import {Location} from "@angular/common";
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";
import {ToasterService} from "app/services/ui/ToasterService";
import {Router} from "@angular/router";

@Component({

  selector: "users-list",
  templateUrl: "../../templates/user-list.component.html",
  styleUrls: ['../../styles/user-list.component.css']
})


export class UserListComponent implements OnInit {

  constructor(private userService: UserService,
              private toasterServer: ToasterService,
              private location:Location,
              private router:Router) {
  }

  users: User[] = new Array<User>();

  ngOnInit() {
    this.userService.getRegisteredUsers().subscribe((res) => {

        if (res.status == 200) {
          this.users = res.json();
          console.log(this.users);
        }
      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          console.log("Failed to get users", err);
        }
      });
  }

  showUserInfo(login:string) {
    this.router.navigate(['/admin/users/'+login]);
  }

  removeUser(id: string) {
    this.userService.removeUserById(id).subscribe((res) => {
      if (res.status == 200) {
        this.toasterServer.showToaster("User deleted!");
          this.users = this.users.filter(x => x.userId != Number.parseInt(id));
      }
    }),
      (err) => {
        if (err.status < 200 || err.status > 299) {
          this.toasterServer.showToaster("Failed to delete user!");
        }
      };
  }

}
