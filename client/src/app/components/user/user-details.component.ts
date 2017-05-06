import {Component, OnInit} from "@angular/core";
import {Link} from "../../models/link";
import {AccountDetailsService} from "../../services/account/account-details.service";
import {User} from "../../models/user";
import {Role} from "../../models/role";
import {LinkService} from "../../services/links/link.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";
import {ToasterService} from "../../services/ui/ToasterService";
import {Location} from "@angular/common";
import {ActivatedRoute } from "@angular/router";


@Component({
  selector: 'account-details',
  templateUrl: '../../templates/user-details.component.html',
  styleUrls: ['../../styles/user-details.component.css']
})

export class UserInfoComponent implements OnInit {

  constructor(private accountDetailsService: AccountDetailsService,
              private linkService: LinkService,
              private router: Router,
              private authService: AuthService,
              private toasterService: ToasterService,
              private location:Location,
              private activatedRoute:ActivatedRoute) {
  }

  user: User = new User(0, "", "", new Array<Role>(), new Array<Link>());
  redirectUrl = localStorage.getItem("RedirectUrl");

  ngOnInit() {
    this.activatedRoute.params.subscribe((params) => {

        let param = params['userName'];
        this.accountDetailsService.getUserInfo(param).subscribe(
          (res)=>{
            if(res.status==200){
              this.user=res.json();
            }
        },
      (err)=>{
        if (err.status < 200 || err.status > 299) {
          console.log("Unable to get  user info", err);
          this.router.navigate(['/links'])
        }
      });

          },
          (err) => {
            if (err.status < 200 || err.status > 299) {
              console.log("Unable to param userLogin info", err);
              this.router.navigate(['/links'])
            }
          });

  }



  redirectToUrl(shortLink: string) {
    window.location.href = this.redirectUrl + shortLink;
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }
}