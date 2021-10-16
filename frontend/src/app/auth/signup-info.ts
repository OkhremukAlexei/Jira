export class SignUpInfo {
  name: string;
  login: string;
  role: string[];
  password: string;


  constructor(name: string, login: string, password: string) {
    this.name = name;
    this.login = login;
    this.password = password;
    this.role = ['user'];
  }
}
