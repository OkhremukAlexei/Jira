import { Component, OnInit } from '@angular/core';
import {TasksInfo} from "../tasks-info";
import {TasksService} from "../../services/tasks.service";
import { Router } from '@angular/router';
import {Subscription} from "rxjs";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  listTasks !: TasksInfo[];

  constructor(private taskService : TasksService, private router : Router) {
  }

  ngOnInit(): void {
    this.taskService.getAllTasks()
      .subscribe(data => {
        this.listTasks = data
      });
  }


  updateTask(id: number) {
    this.router.navigate(['tasks/task-details', id]);
  }

  deleteTask(id: number) {

  }
}
