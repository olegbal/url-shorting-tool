import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'searchPipe',
  pure: false
})
export class SearchPipe implements PipeTransform {
  transform(items: any[], criteria: any): any {

    return items.filter(item => {
      for (let key in item) {
        if (("" + item[key]).includes(criteria)) {
          return true;
        }
      }
      return false;
    });
  }
}
