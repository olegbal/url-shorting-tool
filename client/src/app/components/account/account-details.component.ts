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
  styleUrls: ['../../styles/account-details.component.css', '../../styles/spinner.css']
})

export class AccountDetailsComponent implements OnInit {

  constructor(private accountDetailsService: AccountDetailsService,
              private linkService: LinkService,
              private router: Router,
              private authService: AuthService) {
  }

  user: User = new User(0, "", "", new Array<Role>(), new Array<Link>());
  addingLink = new Link(0, "", "", 0, "", "", null);
  editingLink;
  isAdding = false;
  showDialog = false;
  spinnerOn = false;
  existingLinkFullAddres: String;
  changed = false;

  ngOnInit() {
    this.spinnerOn = true;
    this.accountDetailsService.getUserInfo(this.authService.login).subscribe((res) => {
        if (res.status == 200) {
          this.user = res.json();
          this.spinnerOn = false;
        }
      },
      (err) => {

        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        else if (err.status < 200 || err.status > 299) {
          console.log("Failed to get account data", err);
          this.spinnerOn = false;
        }
      });
  }

  redirectToUrl(shortLink: string) {
    window.location.href = localStorage.getItem("RedirectUrl") + shortLink;
  }

  removeLink(id: string) {


    this.linkService.deleteLink(id).subscribe(
      (res) => {

        if (res.status == 200) {

          this.user.links = this.user.links.filter(x => x.linkId != Number.parseInt(id));

          console.log("successfully deleted");
          this.changed=!this.changed;
        }
      },
      (err) => {
        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        else if (err.status < 200 || err.status > 299) {
          console.log("Failed to delete link", err);
          this.spinnerOn = false;
        }

      });
  }

  editLink(id: string, link: Link) {

    link.tags = Array.from(new Set(link.tags.split(' '))).toString().split(',').join(' ');

    this.linkService.updateLink(id, link).subscribe((res) => {
        if (res.status == 200) {
          console.log("link successfully updated");
        }
      },
      (err) => {
        if (err.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        else if (err.status < 200 || err.status > 299) {
          console.log("Failed to update link", err);
          this.spinnerOn = false;
        }

      });
  }

  addLink(id: string, link: Link) {

    link.tags = Array.from(new Set(link.tags.split(' '))).toString().split(',').join(' ');

    this.linkService.checkIfLinkExist(link.originalLink).subscribe(
      (res) => {

        if (res.status == 200) {
          link.creationDate = new Date();
          this.linkService.createLink(id, link).subscribe((res) => {
              if (res.status == 200) {
                console.log("link successfully created");
                link.linkId = res.json().linkId;
                link.shortLink = res.json().shortedLink;
                this.user.links.push(link);
                this.changed=!this.changed;
                this.addingLink = new Link(0, "", "", 0, "", "", null);
              }
            },
            (error) => {

              if (error.status == 403) {
                this.spinnerOn = false;
                this.authService.logout();
                this.router.navigate(['/login']);
              }
              else if (error.status < 200 || error.status > 299) {

                this.flushAddingLink();
                console.log("Cannot create link", error);

              }
            }
          );
        }
      },
      (error) => {

        if (error.status == 403) {
          this.spinnerOn = false;
          this.authService.logout();
          this.router.navigate(['/login']);
        }
        else if (error.status == 409) {

          this.existingLinkFullAddres = window.location.hostname + ':'
            + window.location.port + '/' + error.json().shortLink;
          this.showDialog = true;

          this.flushAddingLink();

        }
        else if (error.status < 200 || error.status > 299) {
          this.flushAddingLink();
          console.log("Cannot create link", error);
        }

      });
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);

  }

  startEditing(link: Link) {
    this.editingLink =
      new Link(link.linkId, link.originalLink,
        link.shortLink, link.clicksCount, link.tags,
        link.summary, link.creationDate, link.idEditing);
  }

  cancelEditing(link: Link) {
    link.originalLink = this.editingLink.originalLink;
    link.summary = this.editingLink.summary;
    link.tags = this.editingLink.tags;
  }

  flushAddingLink() {
    this.addingLink.originalLink = "";
    this.addingLink.summary = "";
    this.addingLink.tags = "";
  }

  cancelAdding(link: Link) {
    this.flushAddingLink();
    this.isAdding = false;
  }

}
