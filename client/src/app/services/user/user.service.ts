import {Injectable} from "@angular/core";
import {Headers, Http, Response} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {Observable} from "rxjs";
import {AuthService} from "../auth/auth.service";


@Injectable()
export class UserService {

  constructor(private http: Http,
              private authService: AuthService) {
  }


  private urlString = "/api/v1/user";

  private headers;

  getRegisteredUsers() {

    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.get(this.urlString + 's', {headers: this.headers});
  }

  removeUserById(id: string) {

    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.delete(this.urlString + '/' + id,{headers:this.headers});
  }

  getUserById(id: string){

    this.headers = new Headers({'Auth': this.authService.token});
    return this.http.get(this.urlString + '/' + id,{headers:this.headers});
  }

}
