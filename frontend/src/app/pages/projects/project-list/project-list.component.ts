import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../../services/projects.service";
import {Router} from "@angular/router";
import {Projects} from "../../../models/projects-info";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  listProjects !: Projects[];

  constructor(private projectService : ProjectsService, private router : Router) {
  }

  ngOnInit(): void {
    this.projectService.getAllProjects()
      .subscribe(data => {
        this.listProjects = data
      });
  }


  updateProject(id: number) {
    this.router.navigate(['projects/project-details', id]);
  }

  deleteProject(id: number) {

  }

}
