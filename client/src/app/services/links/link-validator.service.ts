import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Link} from "app/models/link";

@Injectable()
export class LinkValidatorService {
  constructor(private http: Http) {
  }


  private linksUrl = "/api/v1/links";

  headers: Headers;

  getAllLinks() {
    return this.http.get(this.linksUrl);
  }
}
