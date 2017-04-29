import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Link} from "app/models/link";
import {AuthService} from "../auth/auth.service";

@Injectable()
export class LinkService {
  constructor(private http: Http, private authService: AuthService) {
  }


  private linksUrl = "/api/v1/links";

  headers: Headers;

  getAllLinks() {
    return this.http.get(this.linksUrl);
  }

  updateLink(userId: string, link: Link) {
    this.headers = new Headers({'Content-Type': 'application/json', 'Auth': this.authService.token});
    return this.http.put(this.linksUrl + '?userId=' + userId, JSON.stringify(link), {headers: this.headers})
  }


  createLink(userId: string, link: Link) {
    this.headers = new Headers({'Content-Type': 'application/json', 'Auth': this.authService.token});
    return this.http.post(this.linksUrl + '?userId=' + userId, JSON.stringify(link), {headers: this.headers})
  }

  deleteLink(id: string) {
    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.delete(this.linksUrl + '/' + id, {headers: this.headers});
  }

  getLinkByTag(tag: string) {
    this.headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(this.linksUrl + '?' + 'tag=' + tag);
  }

  getLinkInfo(id: String) {
    return this.http.get(this.linksUrl + '/' + id);
  }

}
