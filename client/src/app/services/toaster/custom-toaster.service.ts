import {Injectable, OnInit} from "@angular/core";
import {Toast, ToasterConfig, ToasterService} from "angular2-toaster";

@Injectable()
export class CustomToasterService {

  constructor(private toasterService: ToasterService) {
  }

  defaultToasterConfig = new ToasterConfig({
    positionClass: 'toast-top-left',
    limit: 5,
    showCloseButton:true,
    timeout: 3000
  });

  popToast(type:string,title: string, body: string) {
    var toast: Toast = {
      type: type,
      title: title,
      body: body
    };
    this.toasterService.pop(toast);
  }

}
