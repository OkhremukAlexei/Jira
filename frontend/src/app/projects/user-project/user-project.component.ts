import { Component, OnInit } from '@angular/core';
import {ProjectsInfo} from "../projects-info";
import {ProjectsService} from "../../services/projects.service";
import {TokenStorageService} from "../../auth/token-storage.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-user-project',
  templateUrl: './user-project.component.html',
  styleUrls: ['./user-project.component.css']
})
export class UserProjectComponent implements OnInit {

  listProjects !: ProjectsInfo[];

  constructor(private projectService : ProjectsService, private token: TokenStorageService) { }

  ngOnInit(): void {
    this.getProjects();
  }

  getProjects() : void {
    this.projectService.getProjectsFromUserId(this.token.getId()).
      subscribe(data => {this.listProjects = data});
  }

}
