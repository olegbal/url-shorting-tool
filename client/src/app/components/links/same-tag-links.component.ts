import {Component, OnInit} from "@angular/core";
import {Link} from "../../models/link";
import {LinkService} from "../../services/links/link.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Location} from "@angular/common";
import {CustomToasterService} from "../../services/toaster/custom-toaster.service";


@Component({
  selector: 'same-tag-links',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css','../../styles/spinner.css']
})

export class SameTagLinksComponent implements OnInit {


  constructor(private linkService: LinkService,
              private router: Router,
              private location: Location,
              private activatedRoute: ActivatedRoute,
              private toasterService: CustomToasterService) {
  }

  links: Link[] = new Array<Link>();
  addingLinks: Link[] = new Array<Link>();
  isInCompleted = true;
  spinnerOn=false;
  page = 0;

  ngOnInit() {
    this.loadLinks();
  }

  loadLinks() {
    this.spinnerOn=true;
    if (this.isInCompleted) {
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
                this.spinnerOn=false;
                this.isInCompleted = false;
              }
              else {
                this.spinnerOn=false;
                this.isInCompleted = true;
              }
            }
          },
          (err) => {
            if (err.status < 200 || err.status > 299) {
              console.log("Cannot get Links by tag " + param, err);
              this.toasterService.popToast("error","Error","Cannot get links by tag");
              this.spinnerOn=false;
              this.router.navigate(['/links']);
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
