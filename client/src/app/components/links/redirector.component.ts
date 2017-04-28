import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'link-list-component',
  templateUrl: '../../templates/link-list.component.html',
  styleUrls: ['../../styles/link-list.component.css']
})


export class RedirectorComponent implements OnInit {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute) {

  }

  ngOnInit() {
    this.activatedRoute.url.subscribe((params) => {
      window.location.href = localStorage.getItem("RedirectUrl") + params[0].path;
    });
  }

}
