import {User} from "./users-info";

export class PartialAccountInfo{
  id: number;
  name: string;
  surname: string;
  email: string;
  user: User;

  constructor(id: number, name: string, surname: string, email: string) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
  }
}
