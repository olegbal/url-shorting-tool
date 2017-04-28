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

  ngOnInit() {
    this.activatedRoute.params.subscribe((res) => {
      let param = res['tagName'];
      this.linkService.getLinkByTag(param).subscribe((res) => {
        this.links = res.json();
      });
    })
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
