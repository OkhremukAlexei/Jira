import { Component, OnInit } from '@angular/core';
import {TasksService} from "../../services/tasks.service";
import {ActivatedRoute, Router} from "@angular/router";
import {TasksInfo} from "../tasks-info";

@Component({
  selector: 'app-task-details',
  templateUrl: './task-details.component.html',
  styleUrls: ['./task-details.component.css']
})
export class TaskDetailsComponent implements OnInit {

  currentTask: any = {};
  isUpdated = false;

  constructor(private taskService : TasksService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.getTask(this.route.snapshot.paramMap.get('id'))
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
