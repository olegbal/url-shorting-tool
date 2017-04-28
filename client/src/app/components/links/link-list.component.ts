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
    });
  }

  showDetails(id: string) {
    localStorage.setItem("LinkId", id.toString());
    this.router.navigate(['/links/' + id])
  }
  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }

  redirectToUrl(shortlink: string) {
    window.location.href = localStorage.getItem("RedirectUrl") + shortlink;
  }
}
