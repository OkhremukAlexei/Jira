import { Component, OnInit } from '@angular/core';
import {TasksService} from "../../../services/tasks.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Projects} from "../../../models/projects-info";
import {ProjectsService} from "../../../services/projects.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {DataTransferService} from "../../../services/data-transfer.service";

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  public currentProject: Projects;
  currentTask: any = {};
  isUpdated = true;

  listProjects !: Projects[];

  isProject = true;

  constructor(private projectService: ProjectsService, private token: TokenStorageService,
              private data: DataTransferService, private taskService : TasksService, private route: ActivatedRoute) {
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
      this.getTask(this.route.snapshot.paramMap.get('id'));
    }

  }

  chooseProject(tempProjects: any) {
    this.data.setProject(tempProjects);
    this.isProject = true;
  }

  getTask(id: string | null): void {
    this.taskService.getTaskById(id)
      .subscribe(data => {
          this.currentTask = data;
          console.log(data);
        },
        error => {
          console.log(error);
        })
  }

  updateTask(): void {
    this.taskService.updateTask(this.currentTask.id, this.currentTask);
    this.isUpdated = true;
  }
}
