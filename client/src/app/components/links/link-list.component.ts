import {Component, OnInit} from "@angular/core";
import {LinkService} from "../../services/links/link.service";
import {Link} from "app/models/link";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'link-list-component',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css']
})


export class LinkListComponent implements OnInit {

  constructor(private linkService: LinkService,
              private router: Router,
              private location: Location) {
  }

  links: Link[] = new Array<Link>();
  redirectUrl = localStorage.getItem("RedirectUrl");

  ngOnInit() {
    this.linkService.getAllLinks().subscribe((res) => {
        this.links = res.json();
      },
      (err) => {
        if (err.status < 200 || err.status > 299) {
          console.log("Cannot get links", err)
        }
      });
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
