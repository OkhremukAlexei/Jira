export class UserInfo {
  login: string;
  password: string;
  roles: string;


  constructor(login: string, password: string, roles: string) {
    this.login = login;
    this.password = password;
    this.roles = roles;
  }
}
