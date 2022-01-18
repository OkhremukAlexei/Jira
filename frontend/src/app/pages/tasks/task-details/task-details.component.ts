import { Component, OnInit } from '@angular/core';
import {TasksService} from "../../../services/tasks.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TasksInfo} from "../../../models/tasks-info";
import {Projects} from "../../../models/projects-info";
import {UserService} from "../../../services/user.service";
import {ProjectsService} from "../../../services/projects.service";
import {TokenStorageService} from "../../../services/token-storage.service";
import {DataTransferService} from "../../../services/data-transfer.service";
import {User} from "../../../models/users-info";

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  currentTask: any = {};
  isUpdated = false;

  public currentProject: Projects;
  listProjects !: Projects[];

  isProject = false;

  constructor(private projectService: ProjectsService, private token: TokenStorageService,
              private data: DataTransferService, private taskService : TasksService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.data.currentProject.subscribe(project => this.currentProject = project);

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
