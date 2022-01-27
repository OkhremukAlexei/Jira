import {Time} from "@angular/common";
import {User} from "./users-info";
import {Projects} from "./projects-info";

export class TasksInfo {
  id: number;
  title: string;
  description: string;
  dateTime: string;
  spentTime: Time;
  user: User;
  userName: string;
  project: Projects;
  status: string


  constructor(id: number,title: string, description: string, dateTime: string, spentTime: Time, user: User, project: Projects, status: string) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.dateTime = dateTime;
    this.spentTime = spentTime;
    this.user = user;
    this.project = project;
    this.status = status;
    this.userName = this.user.account.name
  }
}


