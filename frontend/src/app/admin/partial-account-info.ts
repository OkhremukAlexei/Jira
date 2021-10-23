export class PartialAccountInfo{
  login: string;
  roles: string[];
  password: string;


  constructor(login: string, password: string, roles: string[]) {
    this.login = login;
    this.password = password;
    this.roles = ['user'];
  }
}
