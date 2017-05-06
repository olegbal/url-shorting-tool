import {Component, OnInit} from "@angular/core";
import {LinkService} from "../../services/links/link.service";
import {Link} from "app/models/link";
import {Location} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'link-info-component',
  templateUrl: '../../templates/link-info.component.html',
  styleUrls: ['../../styles/link-info.component.css','../../styles/spinner.css']
})


export class LinkInfoComponent implements OnInit {


  constructor(private linkService: LinkService,
              private location: Location,
              private router: Router,
              private activatedRoute: ActivatedRoute) {

  }

  currentLink: Link = new Link(0, "", "", 0, "", "", null);
  redirectUrl = localStorage.getItem("RedirectLink");
  spinnerOn = false;

  ngOnInit() {

    this.activatedRoute.params.subscribe((params) => {
      this.spinnerOn = true;
      let param = params['id'];

      this.linkService.getLinkInfo(param).subscribe((res) => {
          this.currentLink = res.json();
          this.spinnerOn = false;
        },
        (err) => {
          if (err.status < 200 || err.status > 299) {
            console.log("Unable to get link info", err);
            this.spinnerOn = false;
            this.router.navigate(['/'])
          }
        })

    });

  }

  redirectToUrl(shortLink: string) {
    window.location.href = this.redirectUrl + shortLink;
  }

  showLinksWithSameTag(tag: string) {
    this.router.navigate(['/links/tag/' + tag]);
  }
}
