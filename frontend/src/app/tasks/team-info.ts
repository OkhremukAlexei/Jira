export class TeamInfo{
  numberOfPersons: number;
  users: UserInfo[];

  constructor(numberOfPersons: number, users: UserInfo[]) {
    this.numberOfPersons = numberOfPersons;
    this.users = users
  }

}

export class UserInfo{
  login: string;
  roles: Role[];

  constructor(login: string, roles: Role[]) {
    this.login = login;
    this.roles = roles;
  }
}

export class Role{
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}
