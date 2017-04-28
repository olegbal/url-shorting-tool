import {Role} from "./role";
import {Link} from "./link";
export class User {
  constructor(public userId: number,
              public login: string,
              public password: string,
              public roles: Role[],
              public links: Link[]) {
  }
}
