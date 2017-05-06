import {Component, OnInit} from "@angular/core";
import {LinkService} from "../../services/links/link.service";
import {Link} from "app/models/link";
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import {ToasterService} from "../../services/ui/ToasterService";

@Component({
  selector: 'link-list-component',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css']
})


export class LinkListComponent implements OnInit {

  constructor(private linkService: LinkService,
              private router: Router,
              private location: Location,
              private toasterService: ToasterService) {
  }

  links: Link[] = new Array<Link>();
  redirectUrl = localStorage.getItem("RedirectUrl");
  page = 0;
  isInCompleted = true;
  addingLinks: Link[] = new Array<Link>();

  ngOnInit() {
    this.loadLinks();
  }

  loadLinks() {
    if (this.isInCompleted) {
      this.linkService.getAllLinks(this.page).subscribe((res) => {

          if (res.status == 200) {

            this.addingLinks = res.json();

            if (this.addingLinks.length > 0) {


              for (var i = 0; i < this.addingLinks.length; i++) {
                if (!this.links.includes(this.addingLinks[i])) {
                  this.links.push(this.addingLinks[i]);
                }

              }


              this.isInCompleted = false;
              this.page++;
            }
            else {
              this.isInCompleted = true;
            }
          }
        },
        (err) => {
          if (err.status < 200 || err.status > 299) {
            this.toasterService.showToaster("Cannot get link list");
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
