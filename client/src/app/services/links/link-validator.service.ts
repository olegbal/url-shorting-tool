import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";
import {Link} from "app/models/link";

@Injectable()
export class LinkValidatorService {
  constructor(private http: Http) {
  }



  getAllLinks(url:string) {
    return this.http.get(url);
  }
}
