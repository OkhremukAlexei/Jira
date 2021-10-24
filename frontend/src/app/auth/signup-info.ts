export class SignUpInfo {
  name: string;
  login: string;
  roles: string[];
  password: string;


  constructor(name: string, login: string, password: string, isManager: boolean) {
    this.name = name;
    this.login = login;
    this.password = password;
    if(isManager) {
      this.roles = ['manager'];
    }
    else {
      this.roles = ['user'];
    }
  }
}
