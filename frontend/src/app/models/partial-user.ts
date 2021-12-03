export class PartialUserInfo {
  login: string;
  password: string;
  roles: string;


  constructor(login: string, password: string, roles: string) {
    this.login = login;
    this.password = password;
    this.roles = roles;
  }
}
