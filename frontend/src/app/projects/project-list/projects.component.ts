import { Component, OnInit } from '@angular/core';
import {ProjectsInfo} from "../projects-info";
import {ProjectsService} from "../../services/projects.service";
import { Router } from '@angular/router';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  listProjects !: ProjectsInfo[];

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
