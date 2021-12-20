export class User {
  id: number;
  login: string;
  password: string;
  roles: string;
  account: Account;


  constructor(id: number, login: string, password: string, roles: string, account: Account) {
    this.id = id
    this.login = login;
    this.password = password;
    this.roles = roles;
    this.account = account;
  }
}

export class Account{
  name: string;
  surname: string;
  email: string;

  constructor(name: string, surname: string, email: string) {
    this.name = name;
    this.surname = surname;
    this.email = email;
  }
}
