import { Component, OnInit } from '@angular/core';
import {User} from "../../../models/users-info";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";
import {UserService} from "../../../services/user.service";
import {ProjectsService} from "../../../services/projects.service";
import {Observable, Subscription} from "rxjs";
import {map, startWith, switchMap} from "rxjs/operators";
import {Params} from "@angular/router";
import {TokenStorageService} from "../../../services/token-storage.service";

@Component({
  selector: 'app-people',
  templateUrl: './people.component.html',
  styleUrls: ['./people.component.css']
})
export class PeopleComponent implements OnInit {

  public searchFilter: any = '';
  public query: any;
  public listAccount!: User[];
  public currentProject: Projects;
  listProjects !: Projects[];

  isProject = true;

  constructor(private userService: UserService, private projectService: ProjectsService, private token: TokenStorageService,
              private data: DataTransferService) {
  }

  ngOnInit(): void {
    this.data.currentProject.subscribe(project => this.currentProject = project);
    console.log(this.currentProject);
    if (this.currentProject == null) {
      this.isProject = false;
      this.projectService.getProjectsFromUserId(this.token.getId()).
        subscribe(data => {this.listProjects = data});
    }
    else {
      this.getUsers();
    }


  }

  getUsers(): void {
    this.userService.getUsersOutsideProject(this.currentProject.id).subscribe(list => this.listAccount = list);
  }

  addPerson(currentUser: User){
    this.currentProject?.users.push(currentUser);
    this.projectService.addPeopleToProject(this.currentProject);
    console.log(this.currentProject);

    var index = this.listAccount.indexOf(currentUser);
    this.listAccount.splice(index, 1);
  }

  deletePerson(tempUsers: User) {
    this.projectService.deletePeopleInProject(this.currentProject.id, tempUsers.id);

    var index = this.currentProject.users.indexOf(tempUsers);
    this.currentProject.users.splice(index, 1);
    this.listAccount.push(tempUsers);

  }

  chooseProject(tempProjects: any) {
    this.data.setProject(tempProjects);
    this.getUsers();
    this.isProject = true;
  }
}
