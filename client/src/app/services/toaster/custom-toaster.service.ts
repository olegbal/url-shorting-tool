import {Injectable, OnInit} from "@angular/core";
import {Toast, ToasterConfig, ToasterService} from "angular2-toaster";

@Injectable()
export class CustomToasterService implements OnInit {

  constructor(private toasterService: ToasterService) {
  }

  public defaultToasterConfigCenter;
  public defaultToasterConfigBotCenter;

  ngOnInit() {
    this.defaultToasterConfigCenter = new ToasterConfig({
      positionClass: 'toast-center'
    });
    this.defaultToasterConfigBotCenter = new ToasterConfig({
      positionClass: 'toast-bottom-center'
    });
  }

  popToast(type:string,title: string, body: string) {
    var toast: Toast = {
      type: type,
      title: title,
      body: body
    };
    this.toasterService.pop(toast);
  }

}
