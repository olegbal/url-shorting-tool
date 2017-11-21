import {Component, OnInit} from "@angular/core";
import {Link} from "../../models/link";
import {AccountDetailsService} from "../../services/account/account-details.service";
import {User} from "../../models/user";
import {Role} from "../../models/role";
import {LinkService} from "../../services/links/link.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {Location} from "@angular/common";
import {ActivatedRoute} from "@angular/router";
import {CustomToasterService} from "../../services/toaster/custom-toaster.service";


@Component({
  selector: 'account-details',
  templateUrl: '../../templates/user-details.component.html',
  styleUrls: ['../../styles/user-details.component.css', '../../styles/spinner.css']
})

export class UserInfoComponent implements OnInit {

  constructor(private accountDetailsService: AccountDetailsService,
              private linkService: LinkService,
              private router: Router,
              private authService: AuthService,
              private location: Location,
              private activatedRoute: ActivatedRoute,
              private toasterService: CustomToasterService) {
  }

  user: User = new User(0, "", "", new Array<Role>(), new Array<Link>());
  spinnerOn = false;

  ngOnInit() {
    this.spinnerOn = true;
    this.activatedRoute.params.subscribe((params) => {

        let param = params['userName'];
        this.accountDetailsService.getUserInfo(param).subscribe(
          (res) => {
            if (res.status == 200) {
              this.user = res.json();
              this.spinnerOn = false;
            }
          },
          (err) => {

            if (err.status == 403) {
              this.router.navigate(['/login']);
            } else if (err.status < 200 || err.status > 299) {
              console.log("Unable to get  user info", err);
              this.toasterService.popToast("error", "Error", "Unable to get user info");
              this.spinnerOn = false;
              this.router.navigate(['/admin/users'])
            }
          });

      },
      (err) => {

        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        } else if (err.status < 200 || err.status > 299) {
          console.log("Unable to param  info", err);
          this.toasterService.popToast("error", "Error", "Unable to get param info");
          this.spinnerOn = false;
          this.router.navigate(['/admin/users'])
        }
      });

  }


  redirectToUrl(shortLink: string) {
    window.location.href = localStorage.getItem("RedirectUrl") + shortLink;
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }
}
