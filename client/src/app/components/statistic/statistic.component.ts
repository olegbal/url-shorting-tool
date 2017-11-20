import {Component, OnInit} from "@angular/core";
import {Response} from "@angular/http";

import {RegistrationService} from "../../services/registration/registration.service";
import {Router} from "@angular/router";
import {LoginAndPassword} from "../../models/loginAndPassword";
import {StatisticService} from "../../services/statistic/statistic.service";
import {Statistic} from "../../models/statistic";
import {User} from "../../models/user";
import {UserService} from "../../services/user/user.service";

@Component({
  selector: 'statistic-component',
  templateUrl: '../../templates/statistic.component.html',
  styleUrls: ['../../styles/statistic.component.css', '../../styles/spinner.css']
})


export class StatisticComponent implements OnInit {

  statistic: Statistic = new Statistic(0, 0, 0, 0, 0, 0, 0, 0);
  user: User=new User(0,"error","error",[],[]);

  constructor(private statisticService: StatisticService,
              private userService: UserService) {
  }


  ngOnInit(): void {
    this.statisticService.getStatistic().subscribe((res) => {
      if (res.status == 200) {
        this.statistic = res.json();
        this.userService.getUserById(this.statistic.topUserId.toString()).subscribe((res)=>{
          if(res.status==200){
            this.user=res.json();
          }
        })
      }
    });
  }
}
