import { Component, OnInit } from '@angular/core';
import {ProjectsService} from "../../services/projects.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectsInfo} from "../projects-info";

@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  currentProject: any = {};
  isUpdated = false;

  constructor(private projectService : ProjectsService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getProject(this.route.snapshot.paramMap.get('id'))
  }

  getProject(id: string | null): void {
    this.projectService.getProjectById(id)
      .subscribe(data => {
          this.currentProject = data;
          console.log(data);
        },
        error => {
          console.log(error);
        })
  }

  updateProject(): void {
    this.projectService.updateProject(this.currentProject.id, this.currentProject);
    this.isUpdated = true;
  }
}
