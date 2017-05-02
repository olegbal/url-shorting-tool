import {Component, OnInit} from "@angular/core";
import {Link} from "../../models/link";
import {LinkService} from "../../services/links/link.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";


@Component({
  selector: 'same-tag-links',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css']
})

export class SameTagLinksComponent implements OnInit {


  constructor(private linkService: LinkService,
              private router: Router,
              private location: Location,
              private activatedRoute: ActivatedRoute) {
  }

  links: Link[] = new Array<Link>();
  redirectUrl = localStorage.getItem("RedirectUrl");
  addingLinks: Link[] = new Array<Link>();
  isInCompleted = true;
  page = 0;

  ngOnInit() {
    this.loadLinks();
  }

  loadLinks() {
    if(this.isInCompleted){
    this.activatedRoute.params.subscribe((res) => {
      let param = res['tagName'];
      this.linkService.getLinkByTag(param, this.page).subscribe((res) => {

          if (res.status == 200) {

            this.addingLinks = res.json();

            if (this.addingLinks.length > 0) {

              for (var i = 0; i < this.addingLinks.length; i++) {
                if (!this.links.includes(this.addingLinks[i])) {
                  this.links.push(this.addingLinks[i]);
                }
              }

              this.page++;

              this.isInCompleted = false;
            }
            else {
              this.isInCompleted=true;
            }
          }
      },
        (err) => {
          if (err.status < 200 || err.status > 299) {
            console.log("Cannot get Link by tag " + param, err);
            this.router.navigate(['/']);
          }
        });
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
