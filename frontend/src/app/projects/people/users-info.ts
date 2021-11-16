export class UserInfo {
  login: string;
  password: string;
  roles: string;
  account: AccountInfo;


  constructor(login: string, password: string, roles: string, account: AccountInfo) {
    this.login = login;
    this.password = password;
    this.roles = roles;
    this.account = account;
  }
}

export class AccountInfo{
  name: string;
  surname: string;
  email: string;

  constructor(name: string, surname: string, email: string) {
    this.name = name;
    this.surname = surname;
    this.email = email;
  }
}
