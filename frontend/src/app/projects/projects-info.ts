import {TeamInfo} from "./team-info";

export class ProjectsInfo {
  name: string;
  linkToGit: string;
  progress: number;
  team: TeamInfo;


  constructor(name: string, linkToGit: string, progress: number, team: TeamInfo) {
    this.name = name;
    this.linkToGit = linkToGit;
    this.progress = progress;
    this.team = team;
  }
}

