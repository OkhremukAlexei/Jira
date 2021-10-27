export class PartialAccountInfo{
  user_id: number;
  name: string;
  surname: string;
  email: string;

  constructor(user_id: number, name: string, surname: string, email: string) {
    this.user_id = user_id;
    this.name = name;
    this.surname = surname;
    this.email = email;
  }
}
