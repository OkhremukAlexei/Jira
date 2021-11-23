export class SignUpInfo {
  name: string;
  surname: string;
  email: string;
  login: string;
  roles: string[];
  password: string;


  constructor(name: string, surname: string, email: string, login: string, password: string, isManager: boolean) {
    this.name = name;
    this.surname = surname;
    this.email = email;
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
