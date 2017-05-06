import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Link} from "app/models/link";
import {AuthService} from "../auth/auth.service";
import {Subscription} from "rxjs";

@Injectable()
export class LinkService {
  constructor(private http: Http, private authService: AuthService) {
  }


  private linksUrl = "/api/v1/links";
  private params = ["userId=", "tag=", "page=", "size="];

  headers: Headers;


  getAllLinks(page: number) {
    return this.http.get(this.linksUrl + '?' + this.params[2] + page + '&' + this.params[3] + localStorage.getItem("PageSize"));
  }

  getLinkByTag(tag: string, page: number) {
    this.headers = new Headers({'Content-Type': 'application/json'});
    return this.http.get(this.linksUrl + '?' + this.params[1] + tag + '&' + this.params[2] + page + '&' + this.params[3] + localStorage.getItem("PageSize"));
  }

  getLinkInfo(id: string) {
    return this.http.get(this.linksUrl + '/' + id);
  }

  updateLink(userId: string, link: Link) {
    this.headers = new Headers({'Content-Type': 'application/json', 'Auth': this.authService.token});
    return this.http.put(this.linksUrl + '?' + this.params[0] + userId, JSON.stringify(link), {headers: this.headers})
  }

  createLink(userId: string, link: Link) {
    this.headers = new Headers({'Content-Type': 'application/json', 'Auth': this.authService.token});
    return this.http.post(this.linksUrl + '?' + this.params[0] + userId, JSON.stringify(link), {headers: this.headers})
  }

  deleteLink(id: string) {
    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.delete(this.linksUrl + '/' + id, {headers: this.headers});
  }

  checkIfLinkExist(url: string) {
    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.post(this.linksUrl+"/check",JSON.stringify(url), {headers: this.headers});
  }

}
