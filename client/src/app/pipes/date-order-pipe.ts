import {Injectable, Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'orderByDatePipe',
  pure: false
})

@Injectable()
export class OrderByDatePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    let newVal = value.sort((a: any, b: any) => {
      let date1 = new Date(a.creationDate);
      let date2 = new Date(b.creationDate);

      if (date1 < date2) {
        return 1;
      } else if (date1 > date2) {
        return -1;
      } else {
        return 0;
      }
    });

    return newVal;
  }

}
