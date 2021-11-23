export class ProjectsInfo {
  id: number;
  name: string;
  linkToGit: string;
  progress: number;
  numOfPersonsInTeam: number;
  users: UserInfo[];
  manager: UserInfo;


  constructor(id: number,name: string, linkToGit: string, progress: number, numOfPersonsInTeam: number, users: UserInfo[], manager: UserInfo) {
    this.id = id;
    this.name = name;
    this.linkToGit = linkToGit;
    this.progress = progress;
    this.numOfPersonsInTeam = numOfPersonsInTeam;
    this.users = users;
    this.manager = manager
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

