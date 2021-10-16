export class JwtResponse {
  token: string;
  type: string;
  login: string;
  roles: string[];


  constructor(token: string, type: string, login: string, roles: string[]) {
    this.token = token;
    this.type = type;
    this.login = login;
    this.roles = roles;
  }
}
