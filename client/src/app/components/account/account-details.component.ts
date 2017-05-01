import {Component, OnInit} from "@angular/core";
import {Link} from "../../models/link";
import {AccountDetailsService} from "../../services/account/account-details.service";
import {User} from "../../models/user";
import {Role} from "../../models/role";
import {LinkService} from "../../services/links/link.service";
import {Router} from "@angular/router";
import {AuthService} from "../../services/auth/auth.service";


@Component({
  selector: 'account-details',
  templateUrl: '../../templates/account-details.component.html',
  styleUrls: ['../../styles/account-details.component.css']
})

export class AccountDetailsComponent implements OnInit {

  constructor(private accountDetailsService: AccountDetailsService,
              private linkService: LinkService,
              private router: Router,
              private authService: AuthService) {
  }

  user: User = new User(0, "", "", new Array<Role>(), new Array<Link>());
  addingLink = new Link(0, "", "", 0, "", "", null);
  redirectUrl = localStorage.getItem("RedirectUrl");
  isAdding = false;

  ngOnInit() {
     this.accountDetailsService.getUserInfo(this.authService.login).subscribe((res) => {

        if (res.status == 200) {

          this.user = res.json();
        }

      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          console.log("Failed to get account data", err)
        }
      });
  }

  redirectToUrl(shortLink: string) {
    window.location.href = this.redirectUrl + shortLink;
  }

  removeLink(id: string) {

    this.linkService.deleteLink(id).subscribe((res) => {

      if (res.status == 200) {
        this.user.links.splice(this.user.links.indexOf(this.user.links.find(x => x.linkId.toString() === id)) + 1, 1)
        console.log("successfully deleted");
      }
    });
  }

  editLink(id: string, link: Link) {

    link.tags.split(' ').filter(x => x == x);

    this.linkService.updateLink(id, link).subscribe((res) => {
      if (res.status == 200) {
        console.log("link successfully updated");
      }
    });
  }

  addLink(id: string, link: Link) {
    link.creationDate = new Date();
    this.linkService.createLink(id, link).subscribe((res) => {
        if (res.status == 200) {
          console.log("link successfully created");
          link.linkId = res.json().linkId;
          link.shortLink = res.json().shortedLink;
          this.user.links.push(link);
          this.addingLink = new Link(0, "", "", 0, "", "", null);
        }
      },
      (error) => {
        if (error.status < 200 || error.status > 299) {
          console.log("Cannot create link", error);

        }
      },
    );
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }

}
