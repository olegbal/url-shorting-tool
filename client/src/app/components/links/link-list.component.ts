import {Component, OnInit} from "@angular/core";
import {LinkService} from "../../services/links/link.service";
import {Link} from "app/models/link";
import {Router} from "@angular/router";

@Component({
  selector: 'link-list-component',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css','../../styles/spinner.css']
})


export class LinkListComponent implements OnInit {

  constructor(private linkService: LinkService,
              private router: Router) {
  }

  links: Link[] = new Array<Link>();
  page = 0;
  isInCompleted = true;
  spinnerOn=false;
  addingLinks: Link[] = new Array<Link>();

  ngOnInit() {
    this.loadLinks();
  }

  loadLinks() {
    if (this.isInCompleted) {
      this.spinnerOn=true;
      this.linkService.getAllLinks(this.page).subscribe((res) => {



          if (res.status == 200) {

            this.addingLinks = res.json();

            if (this.addingLinks.length > 0) {


              for (var i = 0; i < this.addingLinks.length; i++) {
                if (!this.links.includes(this.addingLinks[i])) {
                  this.links.push(this.addingLinks[i]);
                }

              }

              this.spinnerOn=false;
              this.isInCompleted = false;
              this.page++;
            }
            else {
              this.isInCompleted = true;
              this.spinnerOn=false;
            }
          }
        },
        (err) => {
          if (err.status < 200 || err.status > 299) {
            this.spinnerOn=false;
            console.log("Cannot get links", err)
          }
        });
    }
  }

  showDetails(id: string) {
    this.router.navigate(['/links/' + id])
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }

  redirectToUrl(shortlink: string) {
    window.location.href = localStorage.getItem("RedirectUrl") + shortlink;
  }
}
