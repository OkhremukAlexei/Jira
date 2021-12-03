import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ProjectsService} from "../../../services/projects.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {Projects} from "../../../models/projects-info";


@Component({
  selector: 'app-project-details',
  templateUrl: './project-details.component.html',
  styleUrls: ['./project-details.component.css']
})
export class ProjectDetailsComponent implements OnInit {

  id: number;
  currentProject: Projects;
  isUpdated = true;

  constructor(private projectService : ProjectsService, private route: ActivatedRoute, private data: DataTransferService,
              private router : Router) { }

  ngOnInit(): void {

    this.getProject(this.route.snapshot.params['id']);
    this.data.currentProject.subscribe(project => this.currentProject = project);
    console.log(this.currentProject);
  }

  getProject(id: any): void{
    this.projectService.getProjectById(id)
      .subscribe(
        (project:any) => {
          this.currentProject = project;
          console.log(project);
          this.data.setProject(this.currentProject);
        },
        error => {
          console.log(error);
        });
  }

  updateProject(): void {
    this.projectService.updateProject(this.currentProject.id, this.currentProject);
    this.isUpdated = true;
  }

  addPeople(){
    this.router.navigate(['projects/people']);
  }
}
