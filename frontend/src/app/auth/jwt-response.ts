export class JwtResponse {
  id: number;
  token: string;
  type: string;
  login: string;
  roles: string[];


  constructor(id: number ,token: string, type: string, login: string, roles: string[]) {
    this.id = id;
    this.token = token;
    this.type = type;
    this.login = login;
    this.roles = roles;
  }
}
